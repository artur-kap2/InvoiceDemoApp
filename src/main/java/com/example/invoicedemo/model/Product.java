package com.example.invoicedemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Product name can't be blank")
    @Column(length = 45 , nullable = false, unique = true)
    private String name;

    public Product(Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
