package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import com.isa.estudos.jpa.salejpa.vo.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public List<ProductVO> getProducts() {
        log.info("Sucess: Get products");
        return productMapper.toProductVO(productRepository.findAll());
    }

    public ProductVO getProductByIndex(Long id) {
        ProductEntity productEntity = productFinderWithException(id);
        log.info("Sucess: Get product by id: {}", id);
        return productMapper.toProductVO(productEntity);
    }

    public ProductVO createProduct(ProductVO productVO) {
        ProductEntity productEntity = productMapper.toProductEntity(productVO);
        log.info("Sucess: Create product: {}", productVO);
        return productMapper.toProductVO(productRepository.save(productEntity));
    }

    public void changeProduct(ProductVO productVO, Long id) {
        ProductEntity product = productFinderWithException(id);
        product.setDescription(productVO.getDescription());
        product.setValue(productVO.getValue());
        productMapper.toProductVO(productRepository.save(product));
        log.info("Sucess: Change product: {} {}", productVO, id);
    }

    public void deleteProductByIndex(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        productEntity.ifPresent(productRepository::delete);
        log.info("Sucess: Delete product: {}", id);
    }

    private ProductEntity productFinderWithException(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
