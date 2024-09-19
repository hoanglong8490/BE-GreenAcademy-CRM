package org.green.hr.dto;

import lombok.Data;

import java.util.Date;

public class RewardDisciplineDTO {
    private Long id;
    private String content;
    private Data decisionDate;
    private Float money;
    private String category;
    private String rdImages;
    private Short status;
    private Date createAt;
    private Date updateAt;
    private String rdCode;
    private String employeeCode;
}
