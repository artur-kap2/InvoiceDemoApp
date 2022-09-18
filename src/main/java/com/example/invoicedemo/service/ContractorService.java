package com.example.invoicedemo.service;

import com.example.invoicedemo.model.Contractor;
import com.example.invoicedemo.repository.ContractorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {
    private final ContractorRepository contractorRepository;

    public ContractorService(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    public List<Contractor> getContractorsList() {
        List<Contractor> lista = contractorRepository.findAll();
        return lista;
    }

    public Result saveContractor(Contractor contractor) {

        Optional<Contractor> oldOne = contractorRepository.findByName(contractor.getName());
        if (oldOne.isPresent() && oldOne.get().getId() != contractor.getId()) {
            return new Result<>(contractor, "The contractor named :" + contractor.getName() + " already exists");
        }
        return new Result<>(contractorRepository.save(contractor));

    }

    public Contractor getContractor(Long id) {
        return contractorRepository.findById(id).get();
    }
}
