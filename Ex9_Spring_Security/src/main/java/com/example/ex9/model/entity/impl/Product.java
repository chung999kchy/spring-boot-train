package com.example.ex9.model.entity.impl;


import com.example.ex9.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "code", nullable = false)
    @Size(min = 2, max = 6, message = "The code '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide product code")
    private String code;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 255, message = "The name '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide product name")
    private String name;

    @NotNull(message = "CategoryId is mandatory")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull(message = "InventoryId is mandatory")
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "*Quantity has to be non negative number")
    private Integer quantity;

    @Column(name = "amount_sale", nullable = false)
    @Min(value = 0, message = "*Amount sale has to be non negative number")
    private Integer amountSale;

    public String toString() {
        return "Product : {id=" + getId()
                + ", code=" + code
                + ", name=" + name
                + ", category_id=" + category.getId()
                + ", inventory_id=" + inventory.getId()
                + ", price=" + price
                + ", image=" + image
                + ", description=" + description
                + ", quantity=" + quantity
                + ", amount_sale=" + amountSale
                + ", created_at=" + getCreatedAt()
                + ", updated_at=" + getUpdatedAt() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return getId().equals(product.getId());
    }


}
