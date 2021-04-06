package com.isa.estudos.jpa.salejpa.service;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.repository.ClientRepository;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import com.isa.estudos.jpa.salejpa.vo.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public List<ClientVO> getClients() {
        return clientMapper.toClientVO(clientRepository.findAll());
    }

    public ClientVO getClientByIndex(Long id) {
        ClientEntity clientEntity = clientFinderWithException(id);
        return clientMapper.toClientVO(clientEntity);
    }

    public ClientVO createClient(ClientVO clientVO) {
        ClientEntity clientEntity = clientMapper.toClientEntity(clientVO);
        return clientMapper.toClientVO(clientRepository.save(clientEntity));
    }

    public void changeClient(ClientVO clientVO, Long id) {
        ClientEntity client = clientFinderWithException(id);
        client.setName(clientVO.getName());
        client.setCpf(clientVO.getCpf());
        client.setAddress(clientVO.getAddress());
        clientMapper.toClientVO(clientRepository.save(client));
    }

    public void deleteClientByIndex(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        clientEntity.ifPresent(clientRepository::delete);
    }

    private ClientEntity clientFinderWithException(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }
}
