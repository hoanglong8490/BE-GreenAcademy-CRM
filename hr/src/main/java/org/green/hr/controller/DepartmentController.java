package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.entity.Department;
import org.green.hr.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService iDepartmentService;

    @PostMapping("/create")
    public ResponseEntity<CoreResponse> createDepartment(@RequestBody(required = false) Department department) {

        CoreResponse coreResponse = new CoreResponse().setCode(Constant.SUCCESS).setMessage(Constant.SUCCESS_MESSAGE).setData(this.iDepartmentService.createDepartment(department));

        return ResponseEntity.ok().body(coreResponse);
    }

}
