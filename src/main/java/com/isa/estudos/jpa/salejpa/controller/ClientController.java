package com.isa.estudos.jpa.salejpa.controller;

import com.isa.estudos.jpa.salejpa.service.ClientService;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping
    public ResponseEntity setClient(@RequestBody ClientVO clientVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientVO));
    }
}
