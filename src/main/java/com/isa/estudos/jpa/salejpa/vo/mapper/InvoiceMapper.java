package com.isa.estudos.jpa.salejpa.vo.mapper;

import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import com.isa.estudos.jpa.salejpa.vo.InvoiceVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceVO toInvoiceVO(InvoiceEntity invoiceEntity);

    List<InvoiceVO> toInvoiceVO(Iterable<InvoiceEntity> all);
}
