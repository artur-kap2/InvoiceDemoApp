package com.example.invoicedemo.service;

import com.example.invoicedemo.model.Invoice;
import com.example.invoicedemo.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoicesList(){
        return invoiceRepository.findAll();
    }

    @Transactional
    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long id){
        invoiceRepository.deleteById(id);
    }
    public Invoice getInvoice(Long id) {
        Optional<Invoice> out = invoiceRepository.findById(id);
        return out.orElse(null);
    }
}
