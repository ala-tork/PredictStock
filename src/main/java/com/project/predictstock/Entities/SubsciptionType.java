package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsciptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdSubscriptionType;

    private double amount;

    private String duration;

    private String allowedCompanyNumbers;

    private String allowedArticleNumbers;

    @OneToOne(mappedBy = "subscriptionType")
    private Subscription subscription;
}

