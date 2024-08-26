package com.education;

import com.education.model.response.StudentResponse;
import com.example.core.model.CoreResponse;
import com.example.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EducationClass {

    @Autowired
    private JsonUtil jsonUtil;

    @GetMapping("/education")
    public String hello() {
        CoreResponse<StudentResponse> response = new CoreResponse<>();

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName("Ho Van Quan");
        studentResponse.setEmail("quanhv@lsd.vn");

        response.setCode(200);
        response.setMessage("Thanh Cong");
        response.setData(studentResponse);
        return "Xin chao Education";
    }
}
