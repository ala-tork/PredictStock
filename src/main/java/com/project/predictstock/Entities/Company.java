package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdCompany;

    private String name;

    private Date creationDate;

    private Boolean isEnabled;

    @OneToMany(mappedBy = "company")
    private List<Article> articles;

    @OneToMany(mappedBy = "company")
    private List<Subscription> subscriptions;

    @ManyToOne
    private User user;
}
