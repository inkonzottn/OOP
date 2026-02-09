package com.example.oopnp.controller;

import com.example.oopnp.entity.Manager;
import org.springframework.ui.Model;
import com.example.oopnp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MangerController {

    private final ManagerService managerService;

    @GetMapping("/managers")
    public String getPageManagers(Model model) {

        List<Manager> managers = managerService.findAllManager();
        model.addAttribute("managers", managers);
        return "managers";
    }
}
