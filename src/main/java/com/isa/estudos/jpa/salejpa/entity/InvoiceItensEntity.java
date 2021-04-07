package com.isa.estudos.jpa.salejpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INVOICE_ITENS")
public class InvoiceItensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @Column(name = "creation_date")
    private LocalDate creationDate = LocalDate.now();

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private InvoiceEntity invoice;
}
