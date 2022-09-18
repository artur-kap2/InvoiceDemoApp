package com.example.invoicedemo.repository;

import com.example.invoicedemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select count(*) as found  from InvoiceDetail where products_id = :productId")
    Integer isUsed(@Param("productId") Long productId);

    @Query("select p from Product p where p.name = :productName")
    Optional<Product> findByName(@Param("productName") String productName);
}
