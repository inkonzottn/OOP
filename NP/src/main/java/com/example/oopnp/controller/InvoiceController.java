package com.example.oopnp.controller;

import com.example.oopnp.entity.*;
import com.example.oopnp.repository.InvoiceRepository;
import com.example.oopnp.service.InvoiceService;
import com.example.oopnp.service.ProjectAssignmentService;
import com.example.oopnp.service.ProjectService;
import com.example.oopnp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ProjectService projectService;
    private  final ProjectAssignmentService projectAssignmentService;
    private final UserService userService;

    @GetMapping({"/admin/invoices", "/manager/invoices", "/customer/invoices"})
    public String getPageInvoices(Principal principal, Authentication auth, Model model) {

        User currentUser = userService.findUserByEmail(principal.getName());
        Long currentUserId = currentUser.getId();
        List<Invoice> invoices;

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_admin"))) {
            invoices = invoiceService.findAllInvoices();

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_manager"))) {
            invoices = invoiceService.findInvoicesForManager(currentUserId);

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_customer"))) {
            invoices = invoiceService.findInvoicesForCustomer(currentUserId);
        } else {
            invoices = null;
        }

        // відфільтрований список
        model.addAttribute("invoices", invoices);
        return "invoices";
    }


    @GetMapping("/{rolePath}/invoices/{id}")
    public String viewInvoiceDetails(@PathVariable("rolePath") String rolePath, @PathVariable Long id, Model model) {
        Invoice invoice = invoiceService.findById(id);

        // дістаємо всі завершені таски по цьому проєкту
        List<ProjectAssignment> completedTasks = projectAssignmentService.findAllProjectAssignmentsByProjectId(invoice.getProject().getId());

        model.addAttribute("invoice", invoice);
        model.addAttribute("tasks", completedTasks);

        return "invoice-details";
    }



    // create
    @GetMapping({"/admin/invoices/create/{projectId}", "/manager/invoices/create/{projectId}"})
    public String getCreateInvoiceForm(@PathVariable Long projectId, Model model) {
        Project project = projectService.findProjectById(projectId);

        // рахуємо собівартість
        Double devCosts = invoiceService.calculateDevCosts(projectId);

        model.addAttribute("project", project);
        model.addAttribute("devCosts", devCosts);

        return "invoice-create";
    }


    @PostMapping("/manager/invoices/create/{projectId}")
    public String createInvoice(@PathVariable Long projectId,
                                @RequestParam("finalPrice") Double finalPrice,
                                RedirectAttributes redirectAttributes) {
        try {
            Project project = projectService.findProjectById(projectId);
            invoiceService.saveNewInvoice(projectId, finalPrice);

            redirectAttributes.addFlashAttribute("successMessage", "Рахунок успішно виставлено!");
            return "redirect:/manager/projects";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/manager/invoices/create/" + projectId;
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/manager/projects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/manager/projects";
        }
    }


    // update
    @GetMapping({"/admin/invoices/edit/{projectId}", "/manager/invoices/edit/{id}"})
    public String getEditInvoiceForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        String rolePath = isAdmin ? "admin" : "manager";

        try {
            Invoice invoice = invoiceService.findById(id);

            // не можна редагувати оплачені чи відхилені рахунки
            if (invoice.getStatus() == InvoiceStatus.PAID | invoice.getStatus() == InvoiceStatus.CANCELLED) {
                redirectAttributes.addFlashAttribute("errorMessage", "Неможливо змінити рахунок, який вже оплачено чи відхилено!");
                return "redirect:/" + rolePath + "/invoices";
            }


            model.addAttribute("invoice", invoice);
            return "invoice-edit";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/" + rolePath + "/invoices";
        }
    }

    @PostMapping("/{rolePath}/invoices/edit/{id}")
    public String updateInvoice(@PathVariable("rolePath") String rolePath,
                                @PathVariable Long id,
                                @RequestParam("finalPrice") Double finalPrice,
                                @RequestParam("status") String status,
                                RedirectAttributes redirectAttributes) {

        try {
            invoiceService.updateInvoice(id, finalPrice, status);
            redirectAttributes.addFlashAttribute("successMessage", "Суму рахунку успішно оновлено!");
            return "redirect:/" + rolePath + "/invoices";

        } catch (IllegalStateException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/" + rolePath + "/invoices/edit/" + id;
        }
    }

    // pay
    @PostMapping ("/customer/invoices/{id}/pay")
    public String payInvoice (@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            invoiceService.payInvoice(id);
            redirectAttributes.addFlashAttribute("successMessage", "Рахунок успішно оплачено!");
            return "redirect:/customer/invoices";
        } catch (IllegalStateException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/customer/invoices";
        }
    }
}
