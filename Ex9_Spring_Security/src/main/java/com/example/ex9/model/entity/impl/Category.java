package com.example.ex9.model.entity.impl;

import com.example.ex9.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "code", nullable = false)
    @Size(min = 2, max = 6, message = "The code '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide category code")
    private String code;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 255, message = "The name '${validatedValue}' must be between {min} and {max} characters long")
    @NotEmpty(message = "*Please provide category name")
    private String name;

    @Column(name = "description")
    private String description;

    public String toString() {
        return "Category : {id=" + getId()
                + ", code=" + code
                + ", name=" + name
                + ", description=" + description
                + ", created_at=" + getCreatedAt()
                + ", updated_at=" + getUpdatedAt() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return getId().equals(category.getId());
    }
}
