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
        if (invoice.getDetails().size() > 0 ){
            Integer year = invoice.getCreateDate().getYear();
            invoice.setNumber(String.format("%d-%07d",year,invoiceRepository.GET_NEXT_NUMBER(year)));
        }
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
