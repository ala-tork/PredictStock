package com.project.predictstock.Services.Imp;

import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Entities.SubscriptionType;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Repositories.SubscriptionRepository;
import com.project.predictstock.Repositories.SubscriptionTypeRepository;
import com.project.predictstock.Services.CompanyService;
import com.project.predictstock.Services.SubscriptionService;
import com.project.predictstock.Services.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscriptionTypeServiceImp implements SubscriptionTypeService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public SubscriptionType saveSubscriptionType(SubscriptionType subscriptionType) {
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public List<SubscriptionType> fetchSubscriptionTypeList() {
        return (List<SubscriptionType>) subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType updateSubscriptionType(SubscriptionType updatedSubscriptionType, String IdSubscriptionType) {
        SubscriptionType existingSubscriptionType = subscriptionTypeRepository.findById(IdSubscriptionType).orElse(null);
        if (existingSubscriptionType != null) {

            existingSubscriptionType.setAmount(updatedSubscriptionType.getAmount());
            existingSubscriptionType.setDuration(updatedSubscriptionType.getDuration());
            existingSubscriptionType.setAllowedCompanyNumbers(updatedSubscriptionType.getAllowedCompanyNumbers());
            existingSubscriptionType.setAllowedArticleNumbers(updatedSubscriptionType.getAllowedArticleNumbers());


            return subscriptionTypeRepository.save(existingSubscriptionType);
        } else {
            return null;
        }
    }

    // Implement the deleteSubscriptionTypeById method if needed
  //  @Override
   // public void deleteSubscriptionTypeById(String IdSubscriptionType) {
        // Implement the logic to delete a subscription type by its ID from the data store
        // You can use subscriptionTypeRepository.deleteById(IdSubscriptionType) or similar methods here.
    //}
}