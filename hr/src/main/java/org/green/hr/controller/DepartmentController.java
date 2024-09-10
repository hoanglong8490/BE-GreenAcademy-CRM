package org.green.hr.controller;

import static org.green.core.constant.Constant.BAD_REQUEST;
import static org.green.core.constant.Constant.SUCCESS;

import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.DepartmentDTO;
import org.green.hr.service.IDepartmentService;
import org.green.hr.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hr/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private IDepartmentService iDepartmentService;

  @PostMapping("/addDepartment")
  ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
    CoreResponse response = new CoreResponse();
    boolean success = iDepartmentService.addDepartment(departmentDTO);
    if (success) {
      response.setCode(SUCCESS);
      response.setMessage("Department added successfully");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
      response.setCode(BAD_REQUEST);
      response.setMessage("Department already exists");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/updateDepartment/{departmentId}")
  ResponseEntity<?> updateDepartment(@PathVariable("departmentId") long departmentId,
      @RequestBody DepartmentDTO departmentDTO) {
    CoreResponse response = new CoreResponse();
    boolean success = iDepartmentService.updateDepartment(departmentId, departmentDTO);
    if (success) {
      response.setCode(SUCCESS);
      response.setMessage("Department updated successfully");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
      response.setCode(BAD_REQUEST);
      response.setMessage("Department not found");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/deleteDepartment/{departmentId}")
  ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") long departmentId) {
    CoreResponse response = new CoreResponse();
    boolean success = departmentService.deleteDepartment(departmentId);
    if (success) {
      response.setCode(SUCCESS);
      response.setMessage("Department deleted successfully");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
      response.setCode(BAD_REQUEST);
      response.setMessage("Department not found");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getAllDepartment")
  ResponseEntity<?> getAllDepartment() {
    CoreResponse response = new CoreResponse();
    response.setCode(SUCCESS);
    response.setMessage("Department list retrieved successfully");
    response.setData(iDepartmentService.getAllDepartment());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/getDepartment/{departmentId}")
  ResponseEntity<?> getDepartmentById(@PathVariable("departmentId") long departmentId) {
    CoreResponse response = new CoreResponse();
    response.setCode(SUCCESS);
    response.setMessage("Department retrieved successfully");
    response.setData(iDepartmentService.getDepartmentById(departmentId));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
