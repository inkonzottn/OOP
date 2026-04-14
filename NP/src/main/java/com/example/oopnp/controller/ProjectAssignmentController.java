package com.example.oopnp.controller;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Project;
import com.example.oopnp.entity.ProjectAssignment;
import com.example.oopnp.entity.User;
import com.example.oopnp.repository.DeveloperRepository;
import com.example.oopnp.repository.ProjectAssignmentRepository;
import com.example.oopnp.repository.ProjectRepository;
import com.example.oopnp.service.ProjectService;
import com.example.oopnp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.oopnp.service.ProjectAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProjectAssignmentController {

    private final ProjectAssignmentService projectAssignmentService;
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final UserService userService;
    private final DeveloperRepository developerRepository;

    @GetMapping({"/admin/project-assignments", "/manager/project-assignments", "/developer/project-assignments", "/customer/project-assignments"})
    public String getPageProjectAssignment(Model model, Principal principal) {

        User currentUser = userService.findUserByEmail(principal.getName());
        List<ProjectAssignment> projectAssignments;

        switch (currentUser.getRoles().stream().findFirst().get().getName()) {
            case "ROLE_admin":
                projectAssignments = projectAssignmentService.findAllProjectAssignments();
                break;

            case "ROLE_developer":
                projectAssignments = projectAssignmentService.findAllTeamTasksByUserId(currentUser.getId());

                Developer dev = developerRepository.findByUserId(currentUser.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Розробника не знайдено"));
                model.addAttribute("hasActiveProject", dev.getCurrentProject() != null);
                break;

            case "ROLE_manager":
                projectAssignments = projectAssignmentService.findProjectAssignmentsForManager(currentUser.getId());
                break;

            case "ROLE_customer":
                projectAssignments = projectAssignmentService.findProjectAssignmentsForCustomer(currentUser.getId());
                break;

            default:
                projectAssignments = List.of();
        }

        model.addAttribute("projectAssignments", projectAssignments);
        return "project-assignments";
    }


    // create (start)
    @GetMapping("/developer/project-assignments/create")
    public String getCreateForm(Model model, Principal principal) {

        User currentUser = userService.findUserByEmail(principal.getName());
        Developer developer = developerRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Розробника не знайдено"));
        Project currentProject = developer.getCurrentProject();

        if (currentProject == null) {
            model.addAttribute("message", "У вас зараз немає активного проєкту. Ви не можете створювати завдання.");
            return "redirect:/developer/project-assignments";
        }

        model.addAttribute("currentProject", currentProject);
        model.addAttribute("assignment", new ProjectAssignment());

        return "project-assignment-create";
    }

    @PostMapping("/developer/project-assignments/create")
    public String createProjectAssignment(@Valid @ModelAttribute("assignment") ProjectAssignment projectAssignment,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) {

        User currentUser = userService.findUserByEmail(principal.getName());
        Developer developer = developerRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Розробника не знайдено"));
        Project currentProject = developer.getCurrentProject();

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);
            model.addAttribute("currentProject", currentProject);

            return "project-assignment-create";
        }

        try {
            projectAssignmentService.startProjectAssignment(projectAssignment, currentUser.getId(), currentProject.getId());

            return "redirect:/developer/project-assignments";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("currentProject", currentProject);
            return "project-assignment-create";
        }
    }


    // update
    @GetMapping("/developer/project-assignments/edit/{id}")
    public String getEditForm (@PathVariable Long id, Model model, Principal principal) {

        User currentUser = userService.findUserByEmail(principal.getName());
        ProjectAssignment assignment = projectAssignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Завдання не знайдено"));

        if (!assignment.getDeveloper().getUser().getId().equals(currentUser.getId())) {
            return "redirect:/developer/project-assignments";
        }

        if (!assignment.isActive()) {
            return "redirect:/developer/project-assignments";
        }

        model.addAttribute("assignment", assignment);
        return "project-assignment-edit";
    }

    @PostMapping("/developer/project-assignments/edit/{id}")
    public String updateProjectAssignment( @PathVariable Long id,
                                @Valid @ModelAttribute("assignment") ProjectAssignment projectAssignment,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) {

        User currentUser = userService.findUserByEmail(principal.getName());

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);

            ProjectAssignment originalAssignment = projectAssignmentRepository.findById(id).orElseThrow();
            projectAssignment.setProject(originalAssignment.getProject());
            model.addAttribute("assignment", projectAssignment);

            return "project-assignment-edit";
        }

        try {
            projectAssignmentService.updateProjectAssignment(id, projectAssignment, currentUser.getId());

            return "redirect:/developer/project-assignments";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());

            ProjectAssignment originalAssignment = projectAssignmentRepository.findById(id).orElseThrow();
            projectAssignment.setProject(originalAssignment.getProject());
            model.addAttribute("assignment", projectAssignment);

            return "project-assignment-edit";
        }
    }


    // finish
    @PostMapping("/developer/project-assignments/finish/{id}")
    public String finishAssignmentFromModal(@PathVariable("id") Long id,
                                            @RequestParam(defaultValue = "0") Integer spentHours,
                                            @RequestParam(defaultValue = "0") Integer spentMinutes,
                                            Principal principal,
                                            RedirectAttributes redirectAttributes) {
        try {
            User currentUser = userService.findUserByEmail(principal.getName());

            ProjectAssignment formAssignment = new ProjectAssignment();
            formAssignment.setSpentHours(spentHours);
            formAssignment.setSpentMinutes(spentMinutes);

            projectAssignmentService.finishProjectAssignment(id, formAssignment, currentUser.getId());

            redirectAttributes.addFlashAttribute("successMessage", "Завдання успішно завершено!");
            return "redirect:/developer/project-assignments";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/developer/project-assignments";
        }
    }


    //delete
    @PostMapping("/admin/project-assignments/delete/{id}")
    public String deleteAssignment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projectAssignmentService.deleteAssignment(id);

            redirectAttributes.addFlashAttribute("successMessage", "Завдання успішно видалено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/project-assignments";
    }
}
