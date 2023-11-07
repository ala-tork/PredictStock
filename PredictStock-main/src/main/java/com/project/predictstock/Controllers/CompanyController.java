package com.project.predictstock.Controllers;


import com.project.predictstock.Entities.Company;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Entities.User;

import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Services.CompanyService;
import com.project.predictstock.Services.SubscriptionService;
import com.project.predictstock.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Companies")
public class CompanyController {
    @Autowired private CompanyService   CompanyService;



    // Read operation
    @GetMapping("/list")

    public List<Company> fetchCompanyList()
    {
        return CompanyService.fetchCompanyList();
    }
    @PutMapping("/updateCompany/{IdCompany}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable String IdCompany,
            @Valid @RequestBody Company updatedCompany
    ) {
        Company updated = CompanyService.updateCompany(updatedCompany, IdCompany);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public Company createCompany(User user, SubscriptionService subscription, @Valid @RequestBody Company company ) {

        if (subscription != null && subscription.isValid()) {

           company.setUser(user);
           CompanyService.saveCompany(company) ;}
        return company;
        }}

