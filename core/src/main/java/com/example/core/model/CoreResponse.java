package com.example.core.model;

import lombok.Data;

@Data
public class CoreResponse<T> {
    private int code;
    private String message;
    private T data;
}
