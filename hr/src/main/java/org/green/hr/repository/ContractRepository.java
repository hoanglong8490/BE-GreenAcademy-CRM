package org.green.hr.repository;

import org.green.hr.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE LOWER(c.contractCode) LIKE LOWER(:searchTerm) AND c.contractCategory LIKE :contractCategory AND c.salary BETWEEN :minSalary AND :maxSalary")
    Page<Contract> findBySearchTermAndTypeAndSalaryBetween(String searchTerm, String contractCategory, Double minSalary, Double maxSalary, Pageable pageable);

}
