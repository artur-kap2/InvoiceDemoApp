package com.example.invoicedemo.repository;

import com.example.invoicedemo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Procedure(name = "Invoice.GET_NEXT_NUMBER")
    Long GET_NEXT_NUMBER(@Param("YYYY") Integer YYYY);
}
