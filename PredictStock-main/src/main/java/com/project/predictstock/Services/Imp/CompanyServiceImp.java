package com.project.predictstock.Services.Imp;

import com.project.predictstock.Entities.Company;
import com.project.predictstock.Repositories.CompanyRepository;
import com.project.predictstock.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> fetchCompanyList() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company company, String IdCompany) {
        Company existingCompany = companyRepository.findById(IdCompany).orElse(null);
        if (existingCompany != null) {
            existingCompany.setName(company.getName());
            return companyRepository.save(existingCompany);
        } else {
            return null;
        }
    }
  /*  @Override
// Delete operation
    @DeleteMapping("/Companies/{id}")
    public String deleteDepartmentById(@PathVariable("id") String IdCompany) {
        this.deleteCompanyById(IdCompany);
        return "Deleted Successfully";
    }*/
}
