package org.green.hr.repository;

import org.green.hr.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE c.contractCode LIKE :contractName")
    List<Contract> findByContractName(@Param("contractName") String contractName);

    @Modifying
    @Query("UPDATE Contract c SET c.status = 1 WHERE c.id = :id")
    void markAsDeleted(@Param("id") Long id);

    List<Contract> findAll();

    @Query("SELECT c FROM Contract c WHERE c.contractCategory = :contractCategory AND c.salary = :salary")
    List<Contract> findByContractCategoryAndSalary(@Param("contractCategory") String contractCategory, @Param("salary") Float salary);


}
