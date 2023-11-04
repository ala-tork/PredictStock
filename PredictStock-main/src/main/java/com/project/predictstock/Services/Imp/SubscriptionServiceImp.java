package com.project.predictstock.Services.Imp;

import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Repositories.SubscriptionRepository;
import com.project.predictstock.Services.CompanyService;
import com.project.predictstock.Services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImp implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private Date endSubscriptionDate;

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> fetchSubscriptionList() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }

    @Override
    public Subscription updateSubscription(Subscription updatedSubscription, String IdSubscription) {
        Subscription existingSubscription = subscriptionRepository.findById(IdSubscription).orElse(null);
        if (existingSubscription != null) {
            existingSubscription.setCreationDate(updatedSubscription.getCreationDate());
            existingSubscription.setEndSubscriptionDate(updatedSubscription.getEndSubscriptionDate());
            existingSubscription.setCompany(updatedSubscription.getCompany());
            existingSubscription.setSubscriptionType(updatedSubscription.getSubscriptionType());

            return subscriptionRepository.save(existingSubscription);
        } else {
            return null;
        }
    }
    @Override
    public boolean isValid() {
        Date endSubscriptionDate = this.endSubscriptionDate;
        Date currentDate = new Date();


        return !currentDate.after(endSubscriptionDate);

    }
}