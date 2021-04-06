package com.isa.estudos.jpa.salejpa.controller;

import com.isa.estudos.jpa.salejpa.service.ClientService;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
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
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientVO> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientByIndex(id));
    }

    @PostMapping
    public ResponseEntity setClient(@RequestBody ClientVO clientVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientVO));
    }

    @PutMapping("/{id}")
    public ResponseEntity putClient(@RequestBody ClientVO clientVO,
                                    @PathVariable Long id) {
        clientService.changeClient(clientVO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteClientByIndex(id);
        return ResponseEntity.ok().build();
    }
}
