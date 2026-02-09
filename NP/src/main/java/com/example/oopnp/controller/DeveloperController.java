package com.example.oopnp.controller;

import com.example.oopnp.entity.Developer;
import org.springframework.ui.Model;
import com.example.oopnp.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping("/developers")
    public String getPageDevelopers(Model model) {

        List<Developer> developers = developerService.findAllDevelopers();
        model.addAttribute("developers", developers);

        System.out.println("developers : " + developers);

        return "developers";
    }
}
