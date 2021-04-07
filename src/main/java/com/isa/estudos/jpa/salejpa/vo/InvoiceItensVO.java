package com.isa.estudos.jpa.salejpa.vo;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItensVO {

    private LocalDate creationDate;

    private ProductEntity product;
}
