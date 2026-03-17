package com.example.oopnp.controller;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Qualification;
import com.example.oopnp.entity.Specialization;
import com.example.oopnp.repository.DeveloperRepository;
import com.example.oopnp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.oopnp.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;
    private final DeveloperRepository developerRepository;

    @GetMapping({"/admin/developers", "/manager/developers", "/developer/developers"})
    public String getPageDevelopers(Model model) {

        List<Developer> developers = developerService.findAllDevelopers();
        model.addAttribute("developers", developers);

        return "developers";
    }


    // create
    @GetMapping("/admin/developers/create")
    public String getCreateForm(Model model) {
        model.addAttribute("qualifications", Qualification.values());
        model.addAttribute("specializations", Specialization.values());
        return "developer-create";
    }

    @PostMapping("/admin/developers/create")
    public String create(@Valid @ModelAttribute("developer") Developer developer,
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
            model.addAttribute("developer", developer);
            model.addAttribute("qualifications", Qualification.values());
            model.addAttribute("specializations", Specialization.values());
            return "developer-create";
        }

        try {
            developerService.saveNewDeveloper(developer);
            return "redirect:/admin/developers";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "developer-create";
        }
    }


    // edit
    @GetMapping("/admin/developers/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Розробника не знайдено"));

        model.addAttribute("developer", developer);
        model.addAttribute("qualifications", Qualification.values());
        model.addAttribute("specializations", Specialization.values());
        return "developer-edit";
    }

    @PostMapping("/admin/developers/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("developer") Developer developer,
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
            model.addAttribute("developer", developer);
            model.addAttribute("qualifications", Qualification.values());
            model.addAttribute("specializations", Specialization.values());
            return "developer-create";
        }

        try {
            developerService.updateDeveloper(id, developer);
            return "redirect:/admin/developers";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "developer-edit";
        }
    }


    // delete
    @PostMapping("/admin/developers/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            developerService.deleteDeveloperById(id);
        } catch (Exception e) {
            // якщо видалити не можна (є прив'язка)
            return "redirect:/admin/developers?error=cannot_delete";
        }
        return "redirect:/admin/developers";
    }

}
