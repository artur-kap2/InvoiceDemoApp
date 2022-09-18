package com.example.invoicedemo.repository;

import com.example.invoicedemo.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContractorRepository  extends JpaRepository<Contractor,Long> {

    @Query("select c from Contractor c where c.name = :contractorName")
    Optional<Contractor> findByName(@Param("contractorName") String contractorName);
}
