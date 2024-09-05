package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.entity.Employee;
import org.green.hr.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping
    public ResponseEntity<CoreResponse> getAllEmployees(@RequestParam(defaultValue = "0") int pageNo,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<CoreResponse> createEmployee(@RequestBody(required = false) Employee employee) {
        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(iEmployeeService.createEmployee(employee));

        return ResponseEntity.ok().body(coreResponse);
    }
}
