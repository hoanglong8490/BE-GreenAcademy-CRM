package org.green.hr.controller;

import org.green.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HRController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JsonUtil jsonUtil;
    @GetMapping("hr")
    public String hello(){

        String url = "http://localhost:9001/education";
        return restTemplate.getForObject(url, String.class);
//        return "Hello hr";
    }
}
