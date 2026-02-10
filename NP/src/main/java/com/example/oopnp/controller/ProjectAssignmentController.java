package com.example.oopnp.controller;

import com.example.oopnp.entity.ProjectAssignment;
import org.springframework.ui.Model;
import com.example.oopnp.service.ProjectAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectAssignmentController {

    private final ProjectAssignmentService projectAssignmentService;

    @GetMapping("/project-assignments")
    public String getPageProjectAssignment(Model model) {
        List<ProjectAssignment> projectAssignments = projectAssignmentService.findAllProjectAssignment();
        model.addAttribute("projectAssignments", projectAssignments);
        return "project-assignments";
    }
}
