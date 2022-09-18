package com.example.invoicedemo.repository;

import com.example.invoicedemo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
