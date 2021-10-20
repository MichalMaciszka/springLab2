package org.example.Company.dto;


import lombok.*;
import org.example.Company.entity.Company;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetCompanyResponse {
    private String name;
    private Double budget;

    public static Function<Company, GetCompanyResponse> entityToDtoMapper(){
        return company -> GetCompanyResponse.builder()
                .name(company.getName())
                .budget(company.getBudget())
                .build();
    }
}
