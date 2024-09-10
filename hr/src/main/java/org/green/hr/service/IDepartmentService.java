package org.green.hr.service;

<<<<<<< HEAD
import java.util.List;
import org.green.hr.dto.DepartmentDTO;
=======
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517
import org.green.hr.entity.Department;

public interface IDepartmentService {

<<<<<<< HEAD
  boolean addDepartment(DepartmentDTO departmentDTO);

  boolean updateDepartment(long departmentId, DepartmentDTO departmentDTO);

  boolean deleteDepartment(long departmentId);

  List<DepartmentDTO> getAllDepartment();

  List<DepartmentDTO> getDepartmentById(long departmentId);
=======
    Department createDepartment(Department department);
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517
}
