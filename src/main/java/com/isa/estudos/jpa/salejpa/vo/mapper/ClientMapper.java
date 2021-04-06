package com.isa.estudos.jpa.salejpa.vo.mapper;

import com.isa.estudos.jpa.salejpa.entity.ClientEntity;
import com.isa.estudos.jpa.salejpa.vo.ClientVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toClientEntity(ClientVO clientVO);

    ClientVO toClientVO(ClientEntity clientEntity);
}
