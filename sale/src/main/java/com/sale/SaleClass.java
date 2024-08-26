package com.sale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleClass {
    @GetMapping("/sale")
    public String hello () {

//        call API goi sang education
        return "xin chao Sale";
    }
}
