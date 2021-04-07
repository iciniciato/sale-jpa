package com.isa.estudos.jpa.salejpa.controller;

import com.isa.estudos.jpa.salejpa.service.InvoiceService;
import com.isa.estudos.jpa.salejpa.vo.LinkInvoiceVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity getInvoice() {
        return ResponseEntity.ok(invoiceService.getInvoice());
    }

    @PostMapping
    public ResponseEntity postInvoice(@RequestBody LinkInvoiceVO linkInvoiceVO) {
        return ResponseEntity.ok().body(invoiceService.createInvoice(linkInvoiceVO));
    }
}
