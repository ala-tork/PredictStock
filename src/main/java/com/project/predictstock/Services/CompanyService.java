package com.project.predictstock.Services;

import com.project.predictstock.Entities.Company;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CompanyService {
    // Save operation
    Company saveCompany(Company company);

    // Read operation
    List<Company> fetchCompanyList();

    // Update operation
    Company updateCompany(Company company, String IdCompany);


    // Delete operation
    //void deleteCompanyById(String IdCompany);
}
