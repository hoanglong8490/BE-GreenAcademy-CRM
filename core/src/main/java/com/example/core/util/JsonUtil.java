package com.example.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    @Autowired
    private  ObjectMapper objectMapper;

    public String toString (){
        return "Convert sang String";
    }
}
