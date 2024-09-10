package org.green.hr.repository;

import org.green.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.employeeCode = :employeeCode")
    Optional<Employee> findEmployeesByEmployeeCode(@Param("employeeCode") String employeeCode);

    @Query("SELECT e FROM Employee e WHERE e.employeeCode = :employeeCode")
    Optional<Employee> findEmployeeByCode(@Param("employeeCode") String employeeCode);
}
