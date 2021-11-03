package com.example.ex9.mapper.impl;

import com.example.ex9.model.dto.CategoryDto;
import com.example.ex9.model.entity.impl.Category;
import com.example.ex9.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public final class CategoryMapper extends BaseMapper<Category, CategoryDto> {
    private final ModelMapper modelMapper;

    public CategoryMapper(ApplicationContext context) {
        this.modelMapper = context.getBean(ModelMapper.class);
    }

    public CategoryDto mapEntityIntoDTO(Category entity) {
        return this.modelMapper.map(entity, CategoryDto.class);
    }

    public Category mapDtoIntoEntity(CategoryDto dto){
        return this.modelMapper.map(dto, Category.class);
    }

}