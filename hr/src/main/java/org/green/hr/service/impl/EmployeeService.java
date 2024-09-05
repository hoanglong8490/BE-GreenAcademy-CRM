package org.green.hr.service.impl;

import jakarta.transaction.Transactional;
import org.green.hr.entity.Employee;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Employee createEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> getEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return employeeRepository.findAll(pageable);
    }
}
