package com.example.ex9.model.entity.impl;

import com.example.ex9.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
@Getter
@Setter
public class User extends BaseEntity {
    @Column(name="name")
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(name="username")
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Column(name="email")
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name="password")
    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
