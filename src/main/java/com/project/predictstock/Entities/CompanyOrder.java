package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdCompanyOrder;

    @ManyToOne
    private Article article;
}
