package com.example.invoicedemo.controller;

import com.example.invoicedemo.model.Invoice;
import com.example.invoicedemo.model.InvoiceDetail;
import com.example.invoicedemo.model.Product;
import com.example.invoicedemo.service.ContractorService;
import com.example.invoicedemo.service.InvoiceService;
import com.example.invoicedemo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class InvoiceController {
    private static final String EDIT_FORM =  "invoice_form";
    private final InvoiceService invoiceService;
    private final ContractorService contractorService;
    private final ProductService productService;

    public InvoiceController(InvoiceService invoiceService, ContractorService contractorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.contractorService = contractorService;
        this.productService = productService;
    }


    @GetMapping("/invoices")
    public String viewInvoicesList(Model model) {
        model.addAttribute("invoices", invoiceService.getInvoicesList());

        return "invoices";
    }

    @GetMapping("/invoices/new")
    public String viewCreateInvoice(Model model) {
        Invoice i = new Invoice();
        i.setCreateDate(LocalDate.now());
        List<Product> prompt = productService.getProductsList();
        model.addAttribute("invoice", i);
        model.addAttribute("contractors", contractorService.getContractorsList());
        model.addAttribute("products", prompt);
        return EDIT_FORM;
    }

    @GetMapping("/invoices/edit/{id}")
    public String viewModifyInvoice(@PathVariable("id") final Long id, Model model) {
        Invoice i = invoiceService.getInvoice(id);
        List<Product> prompt = productService.getProductsList();
        model.addAttribute("invoice", i);
        model.addAttribute("contractors", contractorService.getContractorsList());
        model.addAttribute("products", prompt);
        return EDIT_FORM;
    }

    @GetMapping("/invoices/delete/{id}")
    public String viewModifyInvoice(@PathVariable("id") final Long id, RedirectAttributes flash) {

        Invoice i = invoiceService.getInvoice(id);
        if (i == null){
            flash.addFlashAttribute("error", String.format("Invoice with ID :%d not found!",id ));
        } else {
            invoiceService.deleteInvoice(id);
            flash.addFlashAttribute("success", String.format("Invoice :%s was successfuly deleted .", i.getDescription()));
        }
        return "redirect:/invoices";
    }


    @PostMapping("/invoices/save")
    public String saveInvoice(@Valid Invoice invoice,Errors errors , Model model,
                              @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                              @RequestParam(name = "quantity[]", required = false) Integer[] quantities
                               ) {
        if(errors.hasErrors()) {
            return EDIT_FORM;
        }

        List<InvoiceDetail> detailList = new ArrayList<>();
        if (itemId != null) {
            for (int i = 0; i < itemId.length; i++) {
                Product product = productService.getProduct(itemId[i]);
                InvoiceDetail line = new InvoiceDetail();
                line.setProduct(product);
                line.setQuantity(quantities[i]);
                detailList.add(line);
            }

            invoice.setDetails(detailList);
        }
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoices";
    }

}
