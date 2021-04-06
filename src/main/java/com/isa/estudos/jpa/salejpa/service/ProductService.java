package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import com.isa.estudos.jpa.salejpa.vo.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public List<ProductVO> getProducts() {
        return productMapper.toProductVO(productRepository.findAll());
    }

    public ProductVO getClientByIndex(Long id) {
        ProductEntity productEntity = productFinderWithException(id);
        return productMapper.toProductVO(productEntity);
    }

    public ProductVO createproduct(ProductVO productVO) {
        ProductEntity productEntity = productMapper.toProductEntity(productVO);
        return productMapper.toProductVO(productRepository.save(productEntity));
    }

    public void changeProduct(ProductVO productVO, Long id) {
        ProductEntity product = productFinderWithException(id);
        product.setDescription(productVO.getDescription());
        product.setValue(productVO.getValue());
        productMapper.toProductVO(productRepository.save(product));
    }

    public void deleteProductByIndex(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        productEntity.ifPresent(productRepository::delete);
    }

    private ProductEntity productFinderWithException(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
