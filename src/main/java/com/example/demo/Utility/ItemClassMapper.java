package com.example.demo.Utility;

import com.example.demo.DTO.ItemClassDTO;
import com.example.demo.Entity.ItemClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemClassMapper {
    ItemClassMapper INSTANCE = Mappers.getMapper(ItemClassMapper.class);

    @Mapping(source = "companyId", target = "companyId")
    @Mapping(source = "typeOfCode", target = "typeOfCode")
    ItemClass toEntity(ItemClassDTO dto);

    ItemClassDTO toDto(ItemClass itemClass);

    void updateEntityFromDTO(ItemClassDTO dto, @MappingTarget ItemClass entity);
}

