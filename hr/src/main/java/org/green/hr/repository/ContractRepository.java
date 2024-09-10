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
<<<<<<< HEAD

=======
    @Query("SELECT c FROM Contract c JOIN c.employee e WHERE " +
            "(LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.contractCategory) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
            "(c.salary BETWEEN :minSalary AND :maxSalary) AND " +
            "(LOWER(c.contractCategory) LIKE LOWER(CONCAT('%', :contractCategory, '%')))")
    Page<Contract> findBySearchTermAndTypeAndSalaryBetween(
            @Param("searchTerm") String searchTerm,
            @Param("contractCategory") String contractCategory,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary,
            Pageable pageable);
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517

}
