package com.isa.estudos.jpa.salejpa.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceVO {

    private ClientVO client;

    private List<InvoiceItensVO> itens;
}
