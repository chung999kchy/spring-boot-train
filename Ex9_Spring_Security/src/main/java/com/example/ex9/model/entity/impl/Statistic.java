package com.example.ex9.model.entity.impl;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHUNG
 * @version 1.0
 * @since 12/10/2021 - 09:01
 */

@Entity
@Table(name = "statistics")
@Getter
@Setter
public class Statistic implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Generated(GenerationTime.INSERT)
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="inventory_id")
    private Long inventoryId;

    @Column(name="products_number")
    private int productsNumber;
}
