package com.example.oopnp.controller;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Qualification;
import com.example.oopnp.entity.Specialization;
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
    //private final UserService userService;

    @GetMapping({"/admin/developers", "/manager/developers", "/developer/developers"})
    public String getPageDevelopers(Model model) {

        List<Developer> developers = developerService.findAllDevelopers();
        model.addAttribute("developers", developers);

        return "developers";
    }


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
}
