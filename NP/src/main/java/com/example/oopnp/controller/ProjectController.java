package com.example.oopnp.controller;

import com.example.oopnp.entity.*;
import com.example.oopnp.repository.DeveloperRepository;
import com.example.oopnp.repository.ManagerRepository;
import com.example.oopnp.repository.ProjectRepository;
import com.example.oopnp.service.DeveloperService;
import com.example.oopnp.service.ManagerService;
import com.example.oopnp.service.ProjectService;
import com.example.oopnp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ManagerService managerService;
    private final ManagerRepository managerRepository;
    private final DeveloperRepository developerRepository;
    private final DeveloperService developerService;

    @GetMapping({"/admin/projects", "/manager/projects", "/developer/projects", "/customer/projects"})
    public String getPageProjects(Principal principal, Authentication auth, Model model) {

        User currentUser = userService.findUserByEmail(principal.getName());
        Long currentUserId = currentUser.getId();
        List<Project> projects;

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_admin"))) {
            projects = projectService.findAllProjects();

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_manager"))) {
            projects = projectService.findProjectsForManager(currentUserId);

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_developer"))) {
            projects = projectService.findProjectsForDeveloper(currentUserId);

        } else {
            projects = projectService.findProjectsForCustomer(currentUserId);
        }

        // відфільтрований список
        model.addAttribute("projects", projects);

        return "projects";
    }


    // create
    @GetMapping("/customer/projects/create")
    public String getCreateForm(Model model) {
        return "project-create";
    }

    @PostMapping("/customer/projects/create")
    public String createProject(@Valid @ModelAttribute("project") Project project,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);

            return "project-create";
        }

        try {
            User currentUser = userService.findUserByEmail(principal.getName());

            projectService.saveNewProjectAsCustomer(project, currentUser.getId());

            return "redirect:/customer/projects";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "project-create";
        }
    }


    // update
    // (admin)
    @GetMapping("/admin/projects/edit/{id}")
    public String getAdminEditForm(@PathVariable Long id, Principal principal, Authentication auth, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Проєкт не знайдено"));;
        List<Manager> managers = managerRepository.findAll();

        model.addAttribute("project", project);
        model.addAttribute("managers", managers);
        model.addAttribute("statuses", ProjectStatus.values());

        return "admin-project-edit";
    }

    @PostMapping("/admin/projects/edit/{id}")
    public String updateProject(@PathVariable Long id,
                                @Valid @ModelAttribute("project") Project project,
                                BindingResult bindingResult,
                                @RequestParam(value = "managerId", required = false) Long managerId,
                                Model model) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);
            model.addAttribute("project", project);
            model.addAttribute("manager", managerRepository.findAll());
            model.addAttribute("statuses", ProjectStatus.values());
            return "admin-project-edit";
        }

        try {
            projectService.updateProjectByAdmin(id, project, managerId);
            return "redirect:/admin/projects";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("project", project);
            model.addAttribute("manager", managerRepository.findAll());
            model.addAttribute("statuses", ProjectStatus.values());
            return "admin-project-edit";
        }
    }


    // (manager)
    @GetMapping("/manager/projects/edit/{id}")
    public String getMangerEditForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Проєкт не знайдено"));

        List<Developer> freeDevelopers = developerService.findFreeDevelopers();

        model.addAttribute("project", project);
        model.addAttribute("freeDevelopers", freeDevelopers);
        model.addAttribute("statuses", ProjectStatus.values());

        return "manager-project-edit";
    }

    @PostMapping("/manager/projects/edit/{id}")
    public String updateProject(@PathVariable Long id,
                                @Valid @ModelAttribute("project") Project project,
                                BindingResult bindingResult,
                                @RequestParam(value = "developerIds", required = false) List<Long> developerIds,
                                Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);
            model.addAttribute("project", project);
            model.addAttribute("freeDevelopers", developerService.findFreeDevelopers());
            model.addAttribute("statuses", ProjectStatus.values());

            return "manager-project-edit";
        }

        try {
            projectService.updateProjectByManager(id, project, developerIds);
            return "redirect:/manager/projects";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("project", project);
            model.addAttribute("freeDevelopers", developerService.findFreeDevelopers());
            model.addAttribute("statuses", ProjectStatus.values());
            return "manager-project-edit";
        }
    }

}
