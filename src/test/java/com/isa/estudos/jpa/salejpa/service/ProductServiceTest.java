package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    private final String DESCRIPTION = String.valueOf(new Random().nextInt());
    private final BigDecimal VALUE = BigDecimal.valueOf(new Random().nextInt());

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductVO productVO;

    @Test
    public void whenGetProductIsOk(){
        Mockito.when(productRepository.findAll()).thenReturn(List.of());

        productService.getProducts();

        Mockito.verify(productRepository, times(1)).findAll();
    }

    @Test
    public void whenGetProductByIdIsOk(){
        long id = 100;

        ProductEntity product = ProductEntity.builder()
                .description(DESCRIPTION)
                .value(VALUE)
                .build();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.getProductByIndex(id);

        Mockito.verify(productRepository, times(1)).findById(any());
    }
}
