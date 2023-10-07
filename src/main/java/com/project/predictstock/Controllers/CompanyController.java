package com.project.predictstock.Controllers;


import com.project.predictstock.Entities.Company;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired private CompanyService   CompanyService;
    // Save operation
    @PostMapping("/companies")

    public Company saveCompany(
            @Valid @RequestBody Company company)
    {
        return CompanyService.saveCompany(company);
    }

    // Read operation
    @GetMapping("/departments")

    public List<Company> fetchCompanyList()
    {
        return CompanyService.fetchCompanyList();
    }


}
