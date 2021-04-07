package com.isa.estudos.jpa.salejpa.repository;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findByIdIn(List<Long> id);
}
