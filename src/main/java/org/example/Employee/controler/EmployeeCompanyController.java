package org.example.Employee.controler;


import org.example.Company.entity.Company;
import org.example.Company.service.CompanyService;
import org.example.Employee.dto.CreateEmployeeRequest;
import org.example.Employee.dto.GetEmployeeResponse;
import org.example.Employee.dto.GetEmployeesResponse;
import org.example.Employee.dto.UpdateEmployeeRequest;
import org.example.Employee.entity.Employee;
import org.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/companies/{name}/employees")
public class EmployeeCompanyController {
    private EmployeeService employeeService;
    private CompanyService companyService;

    @Autowired
    public EmployeeCompanyController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<GetEmployeesResponse> getEmployees(@PathVariable("name") String name){
        Optional<Company> company = companyService.find(name);
        return company.map(val -> ResponseEntity.ok(GetEmployeesResponse.entityToDtoMapper().apply(employeeService.findAll(val))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEmployeeResponse> getEmployee(@PathVariable("name") String name, @PathVariable("id") Integer id){
        return employeeService.find(name, id)
                .map(val -> ResponseEntity.ok(GetEmployeeResponse.entityToDtoMapper().apply(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@PathVariable("name") String name, @RequestBody CreateEmployeeRequest request, UriComponentsBuilder builder){
        Optional<Company> company = companyService.find(name);
        if(company.isPresent()){
            Employee employee = CreateEmployeeRequest
                    .dtoToEntityMapper(x -> companyService.find(x).orElseThrow(), company::get)
                    .apply(request);
            employee = employeeService.create(employee);
            return ResponseEntity.created(builder.pathSegment("api", "companies", "{name}", "employees", "{id}")
                    .buildAndExpand(company.get().getName(), employee.getId()).toUri()).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("name") String name, @PathVariable("id") Integer id){
        Optional<Employee> employee = employeeService.find(name, id);
        if(employee.isPresent()){
            employeeService.delete(employee.get().getId());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("name") String name, @RequestBody UpdateEmployeeRequest request, @PathVariable("id") Integer id){
        Optional<Employee> employee = employeeService.find(name, id);
        if(employee.isPresent()){
            UpdateEmployeeRequest.dtoToEntityUpdater().apply(employee.get(), request);
            employeeService.update(employee.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
