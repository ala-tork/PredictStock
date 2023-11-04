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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdCustomer;

    private String name;

    @Column(unique = true)
    private String email;

    private String country;

    private String phoneNumber;

    @ManyToOne
    private Activity activity;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrders;
}
