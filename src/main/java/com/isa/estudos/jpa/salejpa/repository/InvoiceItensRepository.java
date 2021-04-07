package com.isa.estudos.jpa.salejpa.repository;

import com.isa.estudos.jpa.salejpa.entity.InvoiceItensEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItensRepository extends CrudRepository<InvoiceItensEntity, Long> {
}
