package com.isa.estudos.jpa.salejpa.vo.mapper;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toClientEntity(ClientVO clientVO);

    ClientVO toClientVO(ClientEntity clientEntity);

    List<ClientVO> toClientVO(Iterable<ClientEntity> clientEntityList);
}
