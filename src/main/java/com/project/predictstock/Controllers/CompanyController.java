package com.project.predictstock.Controllers;


import com.project.predictstock.Entities.Company;
import com.project.predictstock.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;}

        @GetMapping("/")
        public List<Company> listCompanies() {
            return companyRepository.findAll();
        }
        @PostMapping("/add")
        public Company addCompany(@RequestBody Company company) {
            return companyRepository.save(company);
    }
       @DeleteMapping("/delete/{IdCompany}")
       public ResponseEntity<String> deleteCompany(@PathVariable String IdCompany) {
        try {
            companyRepository.deleteById(IdCompany);
            return ResponseEntity.ok("Company deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting company");
        }
    }

}
