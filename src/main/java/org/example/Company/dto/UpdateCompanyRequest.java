package org.example.Company.dto;


import lombok.*;
import org.example.Company.entity.Company;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCompanyRequest {
    private Double budget;

    public static BiFunction<Company, UpdateCompanyRequest, Company> dtoToEntityUpdater(){
        return (company, request) -> {
            company.setBudget(request.getBudget());
            return company;
        };
    }
}
