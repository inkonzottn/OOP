package com.example.oopnp.service;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Invoice;
import com.example.oopnp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    // save
    public void saveNewInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }


    // update
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    //delete
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public void deleteAllInvoices() {
        invoiceRepository.deleteAll();
    }

    // find
    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice findInvoiceByProjectTitle(String projectTitle) {
        return invoiceRepository.findByProjectTitle(projectTitle);
    }

    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.findById(id).get();
    }
}
