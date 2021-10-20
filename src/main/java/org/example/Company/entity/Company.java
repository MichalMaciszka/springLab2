package org.example.Company.entity;


import lombok.*;
import org.example.Employee.entity.Employee;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "companies")
public class Company {
    @Id
    private String name;

    private double budget;

    @OneToMany(mappedBy = "company")
    @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employee> employees;
}
