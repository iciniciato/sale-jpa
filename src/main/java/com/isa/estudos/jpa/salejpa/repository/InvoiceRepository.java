package com.isa.estudos.jpa.salejpa.repository;

import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
}
