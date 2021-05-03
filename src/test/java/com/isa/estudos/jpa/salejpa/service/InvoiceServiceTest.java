package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceItensEntity;
import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ClientRepository;
import com.isa.estudos.jpa.salejpa.repository.InvoiceItensRepository;
import com.isa.estudos.jpa.salejpa.repository.InvoiceRepository;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.InvoiceVO;
import com.isa.estudos.jpa.salejpa.vo.LinkInvoiceVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

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
    private final BigDecimal VALUELINKINVOICE = BigDecimal.valueOf(new Random().nextInt());

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private InvoiceVO invoiceVO;

    @MockBean
    private InvoiceItensEntity invoiceItensEntity;

    @MockBean
    private LinkInvoiceVO linkInvoiceVO;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private InvoiceItensRepository invoiceItensRepository;

    @Test
    public void whenGetInvoicesIsOk() {
        Mockito.when(invoiceRepository.findAll()).thenReturn(List.of());

        invoiceService.getInvoices();

        Mockito.verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void whenGetInvoiceByValueIsOk() {
        long value = 100L;

        Mockito.when(invoiceRepository.findInvoiceByValue(value)).thenReturn(List.of());

        invoiceService.getInvoiceByValue();

        Mockito.verify(invoiceRepository, times(1)).findInvoiceByValue(any());
    }

    @Test
    public void whenGetInvoiceByIdIsOk() {
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

    @Test
    public void whenCreateInvoiceIsOk() {
        List<Long> idProduct = Collections.singletonList(10L);
        long idClient = 15;

        InvoiceItensEntity invoiceItensEntity = InvoiceItensEntity.builder()
                .product(ProductEntity.builder()
                        .description(DESCRIPTION)
                        .value(VALUEPRODUCT)
                        .build()).build();

        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .client(ClientEntity.builder()
                        .name(NAME)
                        .cpf(CPF)
                        .address(ADRESS)
                        .build())
                .value(VALUE)
                .itens(Collections.singletonList(invoiceItensEntity)).build();

        LinkInvoiceVO linkInvoiceVO = LinkInvoiceVO.builder()
                .idProduct(idProduct)
                .idClient(idClient)
                .value(VALUELINKINVOICE)
                .build();

        ClientEntity clientEntity = ClientEntity.builder()
                .name(NAME)
                .cpf(CPF)
                .address(ADRESS)
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .description(DESCRIPTION)
                .value(VALUEPRODUCT)
                .build();

        Mockito.when(clientRepository.findById(idClient)).thenReturn(Optional.of(clientEntity));

        Mockito.when(productRepository.findByIdIn(idProduct)).thenReturn(Collections.singletonList(productEntity));

        Mockito.when(invoiceRepository.save(any())).thenReturn(invoiceEntity);

        Mockito.when(invoiceItensRepository.save(any())).thenReturn(invoiceItensEntity);

        InvoiceVO ret = invoiceService.createInvoice(linkInvoiceVO);

        Assertions.assertEquals(clientEntity.getAddress(), ret.getClient().getAddress());
        Assertions.assertEquals(clientEntity.getCpf(), ret.getClient().getCpf());
        Assertions.assertEquals(clientEntity.getName(), ret.getClient().getName());
        Assertions.assertEquals(productEntity.getDescription(), ret.getItens().get(0).getProduct().getDescription());
        Assertions.assertEquals(productEntity.getValue(), ret.getItens().get(0).getProduct().getValue());
    }

    @Test
    public void whenChangingInvoiceHasNotNullOrEmpty() {
        long id = 15;

        Mockito.when(invoiceRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> invoiceService.changeInvoice(linkInvoiceVO, id));
    }

    @Test
    public void whenDeleteInvoiceIsOk() {
        long id = 15;

        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .client(ClientEntity.builder()
                        .name(NAME)
                        .cpf(CPF)
                        .address(ADRESS)
                        .build())
                .value(VALUE)
                .itens(Collections.singletonList(InvoiceItensEntity.builder()
                        .product(ProductEntity.builder()
                                .description(DESCRIPTION)
                                .value(VALUEPRODUCT)
                                .build())
                        .build()))
                .build();

        Mockito.when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoiceEntity));

        invoiceService.deleteInvoiceByIndex(id);

        Mockito.verify(invoiceRepository, times(1)).delete(any());
    }
}
