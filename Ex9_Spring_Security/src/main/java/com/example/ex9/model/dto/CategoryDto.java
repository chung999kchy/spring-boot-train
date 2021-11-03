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
public class CategoryDto {
    @Min(value = 1, message = "category id must greater than or equal to 1")
    private Long id;

    @Size(min = 3, max = 255, message = "The name '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide category name")
    private String name;


    @Size(min = 2, max = 6, message = "The code '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide category code")
    private String code;

    private String description;

    private Date createdAt;

    private Date updatedAt;

}
