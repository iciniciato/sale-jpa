package com.isa.estudos.jpa.salejpa.repository;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
