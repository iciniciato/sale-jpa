package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceEntity;
import com.isa.estudos.jpa.salejpa.entity.InvoiceItensEntity;
import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.repository.ClientRepository;
import com.isa.estudos.jpa.salejpa.repository.InvoiceItensRepository;
import com.isa.estudos.jpa.salejpa.repository.InvoiceRepository;
import com.isa.estudos.jpa.salejpa.repository.ProductRepository;
import com.isa.estudos.jpa.salejpa.vo.InvoiceVO;
import com.isa.estudos.jpa.salejpa.vo.LinkInvoiceVO;
import com.isa.estudos.jpa.salejpa.vo.mapper.InvoiceItensMapper;
import com.isa.estudos.jpa.salejpa.vo.mapper.InvoiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@AllArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceItensRepository invoiceItensRepository;

    private final InvoiceMapper invoiceMapper;

    private final InvoiceItensMapper invoiceItensMapper;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    public List<InvoiceVO> getInvoice() {
        return invoiceMapper.toInvoiceVO(invoiceRepository.findAll());
    }

    public List<InvoiceVO> findInvoiceByValue() {
        return invoiceMapper.toInvoiceVO(invoiceRepository.findInvoiceByValue(100L));
    }

    public InvoiceVO getInvoiceByIndex(Long id) {
        InvoiceEntity invoiceEntity = invoiceFinderWithExcetion(id);
        return invoiceMapper.toInvoiceVO(invoiceEntity);
    }

    public InvoiceVO createInvoice(LinkInvoiceVO linkInvoiceVO) {
        ClientEntity clientEntity = clientFinderWithException(linkInvoiceVO);
        List<ProductEntity> productEntity = productFinderWithException(linkInvoiceVO.getIdProduct());

        InvoiceEntity invoiceEntity = setInvoiceEntity(linkInvoiceVO, clientEntity);

        List<InvoiceItensEntity> invoiceItensEntities = new ArrayList<>();
        BigDecimal invoiceValue = BigDecimal.ZERO;

        for (ProductEntity product : productEntity) {
            InvoiceItensEntity invoiceItensEntity = new InvoiceItensEntity();
            invoiceItensEntity.setProduct(product);
            invoiceItensEntity.setInvoice(invoiceEntity);
            invoiceItensEntities.add(invoiceItensRepository.save(invoiceItensEntity));
            if(!Objects.isNull(product.getValue())){
                invoiceValue = invoiceValue.add(product.getValue());
            }
        }

        invoiceEntity.setValue(invoiceValue);
        InvoiceVO invoiceVO = invoiceMapper.toInvoiceVO(invoiceRepository.save(invoiceEntity));
        invoiceVO.setItens(invoiceItensMapper.toInvoiceItensVO(invoiceItensEntities));

        return invoiceVO;
    }

    public void changeInvoice(LinkInvoiceVO linkInvoiceVO, Long id) {
        ClientEntity clientEntity = clientFinderWithException(linkInvoiceVO);
        InvoiceEntity changeInvoice = invoiceFinderWithExcetion(id);

        List<ProductEntity> productEntity = productFinderWithException(linkInvoiceVO.getIdProduct());
        BigDecimal invoiceValue = BigDecimal.ZERO;

        invoiceItensRepository.deleteAll(invoiceItensRepository.findByInvoiceId(id));
        for (ProductEntity product : productEntity) {

            InvoiceItensEntity invoiceItensEntity = new InvoiceItensEntity();
            invoiceItensEntity.setProduct(product);
            invoiceItensEntity.setInvoice(changeInvoice);
            invoiceItensRepository.save(invoiceItensEntity);
            if(!Objects.isNull(product.getValue())){
                invoiceValue = invoiceValue.add(product.getValue());
            }
        }

        changeInvoice.setClient(clientEntity);
        changeInvoice.setValue(invoiceValue);
        invoiceRepository.save(changeInvoice);
    }

    public void deleteInvoiceByIndex(Long id) {
        invoiceItensRepository.deleteAll(invoiceItensRepository.findByInvoiceId(id));
        Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(id);
        invoiceEntity.ifPresent(invoiceRepository::delete);
    }

    private InvoiceEntity invoiceFinderWithExcetion(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found"));
    }

    private ClientEntity clientFinderWithException(LinkInvoiceVO linkInvoiceVO) {
        return clientRepository.findById(linkInvoiceVO.getIdClient())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    private List<ProductEntity> productFinderWithException(List<Long> ids) {
        List<ProductEntity> productEntity = productRepository.findByIdIn(ids);
        if (productEntity.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return productEntity;
    }

    private InvoiceEntity setInvoiceEntity(LinkInvoiceVO linkInvoiceVO, ClientEntity clientEntity) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setClient(clientEntity);
        invoiceEntity.setValue(linkInvoiceVO.getValue());
        return invoiceRepository.save(invoiceEntity);
    }
}
