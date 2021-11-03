package com.example.ex9.repository;

import com.example.ex9.model.entity.impl.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CHUNG
 * @version 1.0
 * @since 12/10/2021 - 09:26
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Statistic save(Statistic statistic);
}
