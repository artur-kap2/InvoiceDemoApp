package com.example.invoicedemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="contractors")
@Data
@NoArgsConstructor
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Conctractor name is mandatory field")
    @Column(length = 45 , nullable = false, unique = true)
    private String name;



}
