package com.example.ex9.mapper.impl;

import com.example.ex9.model.dto.CategoryDto;
import com.example.ex9.model.dto.InventoryDto;
import com.example.ex9.model.dto.ProductDto;
import com.example.ex9.model.entity.impl.Product;
import com.example.ex9.mapper.BaseMapper;
import com.example.ex9.service.impl.CategoryServiceImpl;
import com.example.ex9.service.impl.InventoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper extends BaseMapper<Product, ProductDto> {

    private final ApplicationContext context;

    private final ModelMapper modelMapper;

    private final CategoryMapper categoryMapper;

    private final InventoryMapper inventoryMapper;

    public ProductMapper(ApplicationContext context, CategoryMapper categoryMapper, InventoryMapper inventoryMapper) {
        this.context = context;
        this.modelMapper = context.getBean(ModelMapper.class);
        this.categoryMapper = categoryMapper;
        this.inventoryMapper = inventoryMapper;
    }

    public ProductDto mapEntityIntoDTO(Product entity) {
        return this.modelMapper.map(entity, ProductDto.class);
    }

    public Product mapDtoIntoEntity(ProductDto dto){
        CategoryServiceImpl categoryServiceImpl = context.getBean(CategoryServiceImpl.class);
        InventoryServiceImpl inventoryServiceImpl = context.getBean(InventoryServiceImpl.class);
        CategoryDto categoryDto = categoryServiceImpl.findOne(dto.getCategoryId());
        InventoryDto inventoryDto = inventoryServiceImpl.findOne(dto.getInventoryId());

        // when convert from dto to entity, not convert category, inventory by modelMapper.
        Product entity = modelMapper.map(dto, Product.class);
        entity.setCategory(categoryMapper.mapDtoIntoEntity(categoryDto));
        entity.setInventory(inventoryMapper.mapDtoIntoEntity(inventoryDto));
        return entity;
    }
}
