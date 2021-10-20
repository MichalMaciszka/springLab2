package org.example.Company.controller;


import org.apache.coyote.Response;
import org.example.Company.dto.CreateCompanyRequest;
import org.example.Company.dto.GetCompaniesResponse;
import org.example.Company.dto.GetCompanyResponse;
import org.example.Company.dto.UpdateCompanyRequest;
import org.example.Company.entity.Company;
import org.example.Company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<GetCompaniesResponse> getCompanies(){
        return ResponseEntity.ok(GetCompaniesResponse.entityToDtoMapper().apply(companyService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetCompanyResponse> getCompany(@PathVariable("name") String name){
        return companyService.find(name)
                .map(val -> ResponseEntity.ok(GetCompanyResponse.entityToDtoMapper().apply(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("name") String name){
        Optional<Company> company = companyService.find(name);
        if(company.isPresent()){
            companyService.delete(company.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createCompany(@RequestBody CreateCompanyRequest request, UriComponentsBuilder builder){
        Company company = CreateCompanyRequest.dtoToEntityMapper().apply(request);
        companyService.create(company);
        return ResponseEntity.created(builder.pathSegment("api", "companies", "{name}")
                .buildAndExpand(company.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateCompany(@RequestBody UpdateCompanyRequest request, @PathVariable("name") String name){
        Optional<Company> company = companyService.find(name);
        if(company.isPresent()){
            UpdateCompanyRequest.dtoToEntityUpdater().apply(company.get(), request);
            companyService.update(company.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
