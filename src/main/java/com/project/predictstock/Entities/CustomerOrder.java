package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdCustomerOrder;

    private String date;

    private int quantity;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Customer customer;
}
