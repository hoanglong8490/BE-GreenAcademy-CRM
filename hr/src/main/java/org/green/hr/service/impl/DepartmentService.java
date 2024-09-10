package org.green.hr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.green.hr.dto.DepartmentDTO;
import org.green.hr.entity.Department;
import org.green.hr.repository.DepartmentRepository;
import org.green.hr.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {

  @Autowired
  DepartmentRepository departmentRepository;


  @Override
  public boolean addDepartment(DepartmentDTO departmentDTO) {
    try {
      if (departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName()) != null) {
        throw new RuntimeException("DepartmentDTO already exists");
      }
      Department department = new Department();
      department.setDepartmentName(departmentDTO.getDepartmentName());
      department.setDescription(departmentDTO.getDescription());
      department.setStatus(departmentDTO.getStatus());
      int statusBit = departmentDTO.getStatus() ? 1 : 0;
      System.out.println("status:" + departmentDTO.getStatus());
      department.setCreateAt(new Date());
      department.setUpdateAt(new Date());
      departmentRepository.save(department);
      return true;
    } catch (Exception e) {
      throw new RuntimeException("Error when add department: " + e.getMessage());
    }
  }

  @Override
  public boolean updateDepartment(long departmentId, DepartmentDTO departmentDTO) {
    try {
      Department department = departmentRepository.findById(departmentId)
          .orElseThrow(() -> new RuntimeException("Department not found: "));
      department.setDepartmentName(departmentDTO.getDepartmentName());
      department.setDescription(departmentDTO.getDescription());

      department.setStatus(departmentDTO.getStatus());
      department.setUpdateAt(new Date());
      departmentRepository.save(department);
      return true;
    } catch (Exception e) {
      throw new RuntimeException("Error when update department: " + e.getMessage());
    }
  }

  @Override
  public boolean deleteDepartment(long departmentId) {
    try {
      if (!departmentRepository.existsById(departmentId)) {
        throw new RuntimeException("Department not found");
      }
      departmentRepository.deleteById(departmentId);
      return true;
    } catch (Exception e) {
      throw new RuntimeException("Error when delete department: " + e.getMessage());
    }
  }

  @Override
  public List<DepartmentDTO> getAllDepartment() {
    List<Department> departmentList = departmentRepository.findAll();
    return departmentList.stream().map(
        department -> DepartmentDTO.builder().departmentId(department.getId())
            .departmentName(department.getDepartmentName()).description(department.getDescription())
            .status(department.getStatus()).createAt(department.getCreateAt())
            .updateAt(department.getUpdateAt()).build()).collect(Collectors.toList());
  }

  @Override
  public List<DepartmentDTO> getDepartmentById(long departmentId) {
    Optional<Department> departmentList = departmentRepository.findById(departmentId);
    return departmentList.stream().map(
        department -> DepartmentDTO.builder().departmentId(department.getId())
            .departmentName(department.getDepartmentName()).description(department.getDescription())
            .status(department.getStatus()).createAt(department.getCreateAt())
            .updateAt(department.getUpdateAt()).build()).collect(Collectors.toList());
  }
}

