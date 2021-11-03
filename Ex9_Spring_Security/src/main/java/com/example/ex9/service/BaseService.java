package com.example.ex9.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, K> {

    Page<T> findByNameContaining(String name, Pageable pageable);

    T findOne(K id);

    Page<T> findAll(Pageable pageable);

    T create(T entity);

    T updateById(K id, T entity);

    boolean deleteById(K id);

}
