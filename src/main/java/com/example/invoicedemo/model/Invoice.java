package com.example.invoicedemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Invoice Data is mandatory field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @Column(length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "contractors_id", nullable = false)
    @NotNull(message = "Contractor is mandatory field")
    private Contractor contractor;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoices_id")
    private List<InvoiceDetail> details = new ArrayList<>();

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", description='" + description + '\'' +
                ", contractor=" + contractor +
                ", details=" + details +
                '}';
    }
}
