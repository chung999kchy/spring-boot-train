package com.example.ex9.mapper.impl;

import com.example.ex9.model.dto.InventoryDto;
import com.example.ex9.model.entity.impl.Inventory;
import com.example.ex9.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public final class InventoryMapper extends BaseMapper<Inventory, InventoryDto> {
    private final ModelMapper modelMapper;

    public InventoryMapper(ApplicationContext context) {
        this.modelMapper = context.getBean(ModelMapper.class);
    }

    public InventoryDto mapEntityIntoDTO(Inventory entity) {
        return this.modelMapper.map(entity, InventoryDto.class);
    }

    public Inventory mapDtoIntoEntity(InventoryDto dto){
        return this.modelMapper.map(dto, Inventory.class);
    }

}