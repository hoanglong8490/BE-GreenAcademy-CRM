package org.green.hr.service.impl;

import jakarta.transaction.Transactional;
import org.green.hr.entity.Department;
import org.green.hr.repository.DepartmentRepository;
import org.green.hr.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public Department createDepartment(Department department) {
        return this.departmentRepository.save(department);
    }
}
