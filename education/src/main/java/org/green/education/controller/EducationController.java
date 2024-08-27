package org.green.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EducationController {
    @GetMapping("/education")
    public String hello(){
        return "Hello education";
    }
}
