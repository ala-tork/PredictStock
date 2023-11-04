package com.project.predictstock.Services;

import com.project.predictstock.Entities.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription saveSubscription(Subscription subscription);


    List<Subscription> fetchSubscriptionList();


    Subscription updateSubscription(Subscription subscription, String IdSubscription);


    // Delete operation
    //  void deleteCompanyById(String IdCompany);
}
