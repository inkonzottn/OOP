package com.example.oopnp.controller;

import com.example.oopnp.entity.*;
import com.example.oopnp.repository.ManagerRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.oopnp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MangerController {

    private final ManagerService managerService;
    private final ManagerRepository managerRepository;

    @GetMapping({"/admin/managers", "/manager/managers", "/developer/managers"})
    public String getPageManagers(Model model) {

        List<Manager> allManagers = managerRepository.findAllManagersWithProjects();

        // Map, менеджер - активні проєкти
        Map<Manager, List<Project>> managersData = allManagers.stream()
                .collect(Collectors.toMap(
                        manager -> manager, // Ключ
                        manager -> manager.getAllProjects().stream()
                                .filter(p -> p.getStatus() != ProjectStatus.CLOSED)
                                .collect(Collectors.toList())
                ));

        model.addAttribute("managers", managersData);

        return "managers";
    }


    // create
    @GetMapping("/admin/managers/create")
    public String getCreateForm(Model model) {
        return "manager-create";
    }

    @PostMapping("/admin/managers/create")
    public String create(@Valid @ModelAttribute("manager") Manager manager,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);
            model.addAttribute("manager", manager);
            return "manager-create";
        }

        try {
            managerService.saveNewManger(manager);
            return "redirect:/admin/managers";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "manager-create";
        }
    }


    // edit
    @GetMapping("/admin/managers/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {

        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Менеджера не знайдено"));

        model.addAttribute("manager", manager);
        return "manager-edit";
    }

    @PostMapping("/admin/managers/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("manager") Manager manager,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existing, replacement) -> existing
                    ));

            model.addAttribute("errors", errorsMap);
            model.addAttribute("manager", manager);
            return "manager-edit";
        }

        try {
            managerService.updateManager(id, manager);
            return "redirect:/admin/managers";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "manager-edit";
        }
    }


    // delete
    @PostMapping("/admin/managers/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            managerService.deleteManagerById(id);
        } catch (Exception e) {
            // якщо видалити не можна (є прив'язка)
            return "redirect:/admin/managers?error=cannot_delete";
        }
        return "redirect:/admin/managers";
    }

}
