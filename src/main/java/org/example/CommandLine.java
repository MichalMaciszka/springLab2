package org.example;

import org.example.Company.service.CompanyService;
import org.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/*@Component
public class CommandLine implements CommandLineRunner {
    private EmployeeService employeeService;
    private CompanyService companyService;

    @Autowired
    public CommandLine(EmployeeService es, CompanyService cs){
        employeeService = es;
        companyService = cs;
    }

    @Override
    public void run(String... args) throws Exception{
        employeeService.findAll().forEach(System.out::println);

        employeeService.updateFirstname("A", "E3", "xyz");
        companyService.updateBudget(7896458.45, "comp2");

        employeeService.findAll().forEach(System.out::println);
    }
}*/
