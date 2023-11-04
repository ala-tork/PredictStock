package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdUser;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Company> companies;
}

