package com.example.ex9.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    @Min(value = 1, message = "inventory id must greater than or equal to 1")
    private Long id;

    @Size(min = 2, max = 6, message = "The code '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide inventory code")
    private String code;

    @Size(min = 3, max = 255, message = "The name '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide inventory name")
    private String name;

    private String address;

    private Date createdAt;

    private Date updatedAt;
}
