package com.isa.estudos.jpa.salejpa.controller;

import com.isa.estudos.jpa.salejpa.service.InvoiceService;
import com.isa.estudos.jpa.salejpa.vo.InvoiceVO;
import com.isa.estudos.jpa.salejpa.vo.LinkInvoiceVO;
import lombok.AllArgsConstructor;
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
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity getInvoices() {
        return ResponseEntity.ok(invoiceService.getInvoices());
    }

    @GetMapping("/value")
    public ResponseEntity getInvoicesValue() {
        return ResponseEntity.ok(invoiceService.getInvoiceByValue());
    }

    @GetMapping ("/client/{idClient}")
    ResponseEntity getByClient(@PathVariable Long idClient) {
        return  ResponseEntity.ok(invoiceService.getByClient(idClient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceVO> getInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceByIndex(id));
    }

    @PostMapping
    public ResponseEntity postInvoice(@RequestBody LinkInvoiceVO linkInvoiceVO) {
        return ResponseEntity.ok().body(invoiceService.createInvoice(linkInvoiceVO));
    }

    @PutMapping("/{id}")
    public ResponseEntity putInvoice(@RequestBody LinkInvoiceVO linkInvoiceVO,
                                     @PathVariable Long id) {
        invoiceService.changeInvoice(linkInvoiceVO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoiceByIndex(id);
        return ResponseEntity.ok().build();
    }
}
