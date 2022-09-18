package com.example.invoicedemo.controller;

import com.example.invoicedemo.model.Contractor;
import com.example.invoicedemo.service.ContractorService;
import com.example.invoicedemo.service.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
@Slf4j
@Controller
public class ContractorController {

    private static final String EDIT_FORM =  "contractor_form";
    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/contractors")
    public String viewContractorsList(Model model)
    {
        model.addAttribute("contractors",contractorService.getContractorsList() );

        return "contractors";
    }

    @GetMapping("/contractors/new")
    public String viewCreateContractor(Model model){
        model.addAttribute("contractor",new Contractor());
        return EDIT_FORM;
    }

    @GetMapping("/contractors/edit/{id}")
    public String viewModifyContractor(@PathVariable("id") final Long id, Model model){
        model.addAttribute("contractor",contractorService.getContractor(id));
        return EDIT_FORM;
    }


    @PostMapping("/contractors/save")
    public String saveContractor(@Valid Contractor contractor , Errors errors , Model model ){

        if(errors.hasErrors()) {
            return EDIT_FORM;
        }


        Result result = contractorService.saveContractor(contractor);
        if (result.isOk()){
            return "redirect:/contractors";
        } else {
            model.addAttribute("error", String.format(result.getMessage()));
            return EDIT_FORM;

        }
    }

}
