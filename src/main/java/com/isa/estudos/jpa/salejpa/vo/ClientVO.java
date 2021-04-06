package com.isa.estudos.jpa.salejpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVO {

    private String name;

    private String cpf;

    private String address;
}
