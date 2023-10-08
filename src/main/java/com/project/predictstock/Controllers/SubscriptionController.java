package com.project.predictstock.Controllers;


import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Services.CompanyService;
import com.project.predictstock.Services.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions") // Define the base URL path for all subscription-related endpoints
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    // Save operation
    @PostMapping("/save")
    public Subscription saveSubscription(@Valid @RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    // Read operation to fetch subscriptions
    @GetMapping("/list")
    public List<Subscription> fetchSubscriptionList() {
        return subscriptionService.fetchSubscriptionList();
    }
    @PutMapping("/update/{IdSubscription}")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable String IdSubscription,
            @Valid @RequestBody Subscription updatedSubscription
    ) {
        Subscription updated = subscriptionService.updateSubscription(updatedSubscription, IdSubscription);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
