package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

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

        ProductEntity productEntity = ProductEntity.builder()
                .description(DESCRIPTION)
                .value(VALUE)
                .build();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));

        productService.getProductByIndex(id);

        Mockito.verify(productRepository, times(1)).findById(any());
    }

    @Test
    public void whenCreateProductIsOk(){
        ProductVO productVO = ProductVO.builder()
                .description(DESCRIPTION)
                .value(VALUE)
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .description(DESCRIPTION)
                .value(VALUE)
                .build();

        Mockito.when(productRepository.save(any())).thenReturn(productEntity);

        ProductVO ret = productService.createProduct(productVO);

        Assertions.assertEquals(productVO.getDescription(), ret.getDescription());
        Assertions.assertEquals(productVO.getValue(), ret.getValue());
    }

    @Test
    public void whenChangingProductHasNotNullOrEmpty(){
        long id = 10;

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> productService.changeProduct(productVO, id));
    }

    @Test
    public void whenDeleteProductIsOk(){
        long id = 10;

        ProductEntity productEntity = ProductEntity.builder()
                .description(DESCRIPTION)
                .value(VALUE)
                .build();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));

        productService.deleteProductByIndex(id);

        Mockito.verify(productRepository, times(1)).delete(any());
    }
}
