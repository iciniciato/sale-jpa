package com.isa.estudos.jpa.salejpa.controller;

import com.isa.estudos.jpa.salejpa.service.ProductService;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductByIndex(id));
    }

    @PostMapping
    public ResponseEntity postProduct(@RequestBody ProductVO productVO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productVO));
    }

    @PutMapping("/{id}")
    public ResponseEntity putProduct(@RequestBody ProductVO productVO,
                                     @PathVariable Long id) {
        productService.changeProduct(productVO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProductByIndex(id);
        return ResponseEntity.ok().build();
    }
}
