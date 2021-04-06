package com.isa.estudos.jpa.salejpa.vo.mapper;

import com.isa.estudos.jpa.salejpa.entity.ProductEntity;
import com.isa.estudos.jpa.salejpa.vo.ProductVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProductEntity(ProductVO productVO);

    ProductVO toProductVO(ProductEntity productEntity);

    List<ProductVO> toProductVO(Iterable<ProductEntity> productEntityList);
}
