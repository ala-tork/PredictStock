package com.project.predictstock.Services;

import com.project.predictstock.Entities.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    SubscriptionType saveSubscriptionType(SubscriptionType subscriptionType);


    List<SubscriptionType> fetchSubscriptionTypeList();


    SubscriptionType updateSubscriptionType(SubscriptionType subscriptionType, String IdSubscriptionType);



    //  void deleteCompanyById(String IdCompany);
}
