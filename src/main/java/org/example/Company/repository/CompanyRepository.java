package org.example.Company.repository;

import org.example.Company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    List<Company> findAll();

    Optional<Company> findByName(String name);

/*    @Modifying
    @Query("update Company c set c.budget = ?1 where c.name = ?2")
    void setCompanyBudget(Double newVal, String key);*/
}
