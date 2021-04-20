package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.repository.ClientRepository;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceTest {
    private final String CPF = String.valueOf(new Random().nextInt());
    private final String ADRESS = String.valueOf(new Random().nextInt());
    private final String NAME = String.valueOf(new Random().nextInt());

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ClientVO clientVO;

    @Test
    public void whenCreateClientIsOk(){
        ClientVO clientVO =  ClientVO.builder()
                .address(ADRESS)
                .cpf(CPF)
                .name(NAME)
                .build();
        ClientVO ret = clientService.createClient(clientVO);

        Assertions.assertEquals(clientVO.getCpf(), ret.getCpf());
        Assertions.assertEquals(clientVO.getName(), ret.getName());
        Assertions.assertEquals(clientVO.getAddress(), ret.getAddress());
    }

    @Test
    public void whenChangingClientHasNotNullOrEmpty(){
        long id = 100;

        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> clientService.changeClient(clientVO, id));
    }
}