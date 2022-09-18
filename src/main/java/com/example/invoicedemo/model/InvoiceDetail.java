package com.example.invoicedemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "invoice_details")
@NoArgsConstructor
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double amount;
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "products_id")
    private Product product;


    @Override
    public String toString() {
        return "InvoiceDetail{" + "id=" + id + ", amount=" + amount + ", product=" + product + '}';
    }
}
