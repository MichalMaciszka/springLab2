package org.example.Employee.dto;


import lombok.*;
import org.example.Company.entity.Company;
import org.example.Employee.entity.Employee;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateEmployeeRequest {
    private String firstname;
    private String lastname;
    private Double salary;

    public static Function<CreateEmployeeRequest, Employee> dtoToEntityMapper(Function<String, Company>companyFunction, Supplier<Company> companySupplier){
        return request -> Employee.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .salary(request.getSalary())
                .company(companySupplier.get())
                .build();
    }
}
