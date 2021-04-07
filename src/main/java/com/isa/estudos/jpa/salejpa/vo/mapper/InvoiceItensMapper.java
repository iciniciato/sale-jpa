package com.isa.estudos.jpa.salejpa.vo.mapper;

import com.isa.estudos.jpa.salejpa.entity.InvoiceItensEntity;
import com.isa.estudos.jpa.salejpa.vo.InvoiceItensVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceItensMapper {

    List<InvoiceItensVO> toInvoiceItensVO(List<InvoiceItensEntity> invoiceItensEntity);
}
