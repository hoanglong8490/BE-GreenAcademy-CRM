package org.green.hr.service;

import java.util.List;
import org.green.hr.dto.DepartmentDTO;
import org.green.hr.entity.Department;

public interface IDepartmentService {

  boolean addDepartment(DepartmentDTO departmentDTO);

  boolean updateDepartment(long departmentId, DepartmentDTO departmentDTO);

  boolean deleteDepartment(long departmentId);

  List<DepartmentDTO> getAllDepartment();

  List<DepartmentDTO> getDepartmentById(long departmentId);
}
