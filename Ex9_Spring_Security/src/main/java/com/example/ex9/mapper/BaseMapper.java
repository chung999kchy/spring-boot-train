package com.example.ex9.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper <Entity, Dto>{

    public abstract Dto mapEntityIntoDTO(Entity entity);

    public abstract Entity mapDtoIntoEntity(Dto dto);

    public List<Dto> mapEntitiesIntoDTOs(Iterable<Entity> entities) {
        List<Dto> dtoList = new ArrayList<>();
        entities.forEach(e -> dtoList.add(mapEntityIntoDTO(e)));
        return dtoList;
    }

    public Page<Dto> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Entity> source) {
        List<Dto> dtoList = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtoList, pageRequest, source.getTotalElements());
    }
}
