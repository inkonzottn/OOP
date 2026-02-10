package com.example.oopnp.repository;

import com.example.oopnp.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByProjectTitle(String projectTitle);
}
