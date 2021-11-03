package com.example.ex9.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    Page<T> findAll(Pageable pageable);

    Page<T> findAllByNameContaining(String name, Pageable pageable);

    boolean existsById(Long id);

    void deleteById(Long id);

}
