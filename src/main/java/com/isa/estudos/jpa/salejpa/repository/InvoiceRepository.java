package com.isa.estudos.jpa.salejpa.repository;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
    @Query(value ="select * from sale_jpa.invoice where value >:valor", nativeQuery = true)
    List<InvoiceEntity> findInvoiceByValue(Long valor);

//    @Query(value = "select invoice from InvoiceEntity invoice inner join fetch invoice.client")
//    List<InvoiceEntity> findInvoices();

    List<InvoiceEntity> findByClient(ClientEntity client);
}
