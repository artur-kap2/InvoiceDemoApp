package com.example.invoicedemo.repository;

import com.example.invoicedemo.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
