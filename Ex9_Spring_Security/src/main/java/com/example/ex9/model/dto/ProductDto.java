package com.example.ex9.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Min(value = 1, message = "product id must greater than or equal to 1")
    private Long id;

    @Min(value = 1, message = "category id must greater than or equal to 1")
    private Long categoryId;

    @Min(value = 1, message = "inventory id must greater than or equal to 1")
    private Long inventoryId;

    @Size(min = 2, max = 6, message = "The code '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide product code")
    private String code;

    @Size(min = 3, max = 255, message = "The name '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide product name")
    private String name;

    private String description;

    @Min(value = 0, message = "price must greater than or equal to 0")
    private BigDecimal price;

    @Min(value = 0, message = "quantity must greater than or equal to 0")
    private int quantity;

    @Min(value = 0, message = "amount_sale must greater than or equal to 0")
    private int amountSale;

    private String image;

    private Date createdAt;

    private Date updatedAt;


}
