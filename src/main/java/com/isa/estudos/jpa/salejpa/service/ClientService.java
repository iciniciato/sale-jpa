package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.repository.ClientRepository;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import com.isa.estudos.jpa.salejpa.vo.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public List<ClientVO> getClients() {
        return clientMapper.toClientVO(clientRepository.findAll());
    }

    public ClientVO createClient(ClientVO clientVO) {
        ClientEntity clientEntity = clientMapper.toClientEntity(clientVO);
        return clientMapper.toClientVO(clientRepository.save(clientEntity));
    }
}
