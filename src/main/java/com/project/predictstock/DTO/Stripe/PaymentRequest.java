package com.project.predictstock.DTO.Stripe;

import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private String token;
    private String description;
}
