package org.green.hr.controller;

import org.green.core.model.response.CoreResponse;
import org.green.hr.model.request.AllowanceSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr/allowances")
public class AllowanceController {

    @GetMapping("/search")
    public ResponseEntity<CoreResponse> getAllAllowances(@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                         @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                         @RequestParam(name = "status", required = false, defaultValue = "") Short status,
                                                         @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        AllowanceSearch allowanceSearch = new AllowanceSearch(status, keyword);
        return null;
    }
}
