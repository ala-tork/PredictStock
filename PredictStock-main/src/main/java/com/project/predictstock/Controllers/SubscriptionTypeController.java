package com.project.predictstock.Controllers;


import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Entities.SubscriptionType;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Services.CompanyService;
import com.project.predictstock.Services.SubscriptionService;
import com.project.predictstock.Services.SubscriptionTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptionType")
public class SubscriptionTypeController {
    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    // Save operation
    @PostMapping("/save")
    public SubscriptionType saveSubscriptionType(@Valid @RequestBody SubscriptionType subscriptionType) {
        return subscriptionTypeService.saveSubscriptionType(subscriptionType);
    }

    @GetMapping("/list")
    public List<SubscriptionType> fetchSubscriptionTypeList() {
        return subscriptionTypeService.fetchSubscriptionTypeList();
    }

    @PutMapping("/update/{IdSubscriptionType}")
    public ResponseEntity<SubscriptionType> updateSubscriptionType(
            @PathVariable String IdSubscriptionType,
            @Valid @RequestBody SubscriptionType updatedSubscriptionType
    ) {
        SubscriptionType updated = subscriptionTypeService.updateSubscriptionType(updatedSubscriptionType, IdSubscriptionType);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}