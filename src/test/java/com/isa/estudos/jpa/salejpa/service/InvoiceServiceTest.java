package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceItensEntity;
import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.InvoiceRepository;
import com.isa.estudos.jpa.salejpa.vo.InvoiceVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceServiceTest {
    private final String CPF = String.valueOf(new Random().nextInt());
    private final String ADRESS = String.valueOf(new Random().nextInt());
    private final String NAME = String.valueOf(new Random().nextInt());
    private final BigDecimal VALUE = BigDecimal.valueOf(new Random().nextInt());
    private final String DESCRIPTION = String.valueOf(new Random().nextInt());
    private final BigDecimal VALUEPRODUCT = BigDecimal.valueOf(new Random().nextInt());

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private InvoiceVO invoiceVO;

    @MockBean
    private InvoiceItensEntity invoiceItensEntity;

    @Test
    public void whenGetInvoicesIsOk(){
        Mockito.when(invoiceRepository.findAll()).thenReturn(List.of());

        invoiceService.getInvoices();

        Mockito.verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void whenGetInvoiceByValueIsOk(){
        long value = 100L;

        Mockito.when(invoiceRepository.findInvoiceByValue(value)).thenReturn(List.of());

        invoiceService.findInvoiceByValue();

        Mockito.verify(invoiceRepository, times(1)).findInvoiceByValue(any());
    }

    @Test
    public void whenGetInvoiceByIdIsOk(){
        long id = 15;

        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .client(ClientEntity.builder()
                        .name(NAME)
                        .cpf(CPF)
                        .address(ADRESS)
                        .build())
                .value(VALUE)
                .itens(Collections.singletonList(InvoiceItensEntity
                        .builder()
                        .product(ProductEntity.builder()
                                .description(DESCRIPTION)
                                .value(VALUEPRODUCT)
                                .build())
                        .build()))
                .build();

        Mockito.when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoiceEntity));

        invoiceService.getInvoiceByIndex(id);

        Mockito.verify(invoiceRepository, times(1)).findById(any());
    }
}
