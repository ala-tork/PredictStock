package com.project.predictstock.Services;

import com.project.predictstock.Entities.Subscription;

import java.util.List;

public interface SubscriptionService {
    // Save operation
    Subscription saveSubscription(Subscription subscription);

    // Read operation
    List<Subscription> fetchSubscriptionList();

    // Update operation
    Subscription updateSubscription(Subscription subscription, String IdSubscription);


    // Delete operation
    //  void deleteCompanyById(String IdCompany);
}
