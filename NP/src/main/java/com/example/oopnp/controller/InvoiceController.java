package com.example.oopnp.controller;

import com.example.oopnp.entity.Invoice;
import com.example.oopnp.repository.InvoiceRepository;
import com.example.oopnp.service.InvoiceService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping({"/admin/invoices", "/manager/invoices", "/customer/invoices"})
    public String getPageInvoices(Model model) {
        List<Invoice> invoices = invoiceService.findAllInvoices();
        model.addAttribute("invoices", invoices);
        return "invoices";
    }
}
