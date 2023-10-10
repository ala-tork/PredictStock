package com.project.predictstock.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdSubscription;

    private Date creationDate;

    private Date endSubscriptionDate;

    @ManyToOne
    private Company company;

    @OneToOne
    private SubscriptionType subscriptionType;
}
