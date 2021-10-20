package org.example;

import org.example.Company.entity.Company;
import org.example.Employee.entity.Employee;
import org.example.Company.service.CompanyService;
import org.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public DataInit(EmployeeService es, CompanyService cs){
        employeeService = es;
        companyService = cs;
    }

    @PostConstruct
    private synchronized void init(){
       /* Company comp1 = Company.builder()
                .name("comp1")
                .budget(13034.32)
                .build();
        Company comp2 = Company.builder()
                .name("comp2")
                .budget(78645.43)
                .build();
        companyService.create(comp1);
        companyService.create(comp2);

        Employee e1 = Employee.builder()
                //.id(1)
                .firstname("C")
                .lastname("E1")
                .salary(5279.22)
                .company(comp1)
                .build();
        Employee e2 = Employee.builder()
                //.id(2)
                .firstname("B")
                .lastname("E2")
                .salary(3458.54)
                .company(comp1)
                .build();
        Employee e3 = Employee.builder()
                //.id(3)
                .firstname("A")
                .lastname("E3")
                .salary(1586.45)
                .company(comp2)
                .build();

        employeeService.create(e1);
        employeeService.create(e2);
        employeeService.create(e3);*/

        Company c1 = Company.builder()
                .name("comp1")
                .budget(41857.45)
                .build();

        Company c2 = Company.builder()
                .name("comp2")
                .budget(68575.65)
                .build();

        companyService.create(c1);
        companyService.create(c2);

        Employee e1 = Employee.builder()
                .firstname("C")
                .lastname("E1")
                .salary(5279.22)
                .company(c1)
                .build();

        Employee e2 = Employee.builder()
                .firstname("B")
                .lastname("E2")
                .salary(3458.54)
                .company(c1)
                .build();

        Employee e3 = Employee.builder()
                .firstname("A")
                .lastname("E3")
                .salary(1586.45)
                .company(c2)
                .build();

        employeeService.create(e1);
        employeeService.create(e2);
        employeeService.create(e3);
    }

}
