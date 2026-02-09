package com.example.oopnp.controller;

import com.example.oopnp.entity.Project;
import com.example.oopnp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/projects")
    public String getPageProjects(Model model) {

        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }
}
