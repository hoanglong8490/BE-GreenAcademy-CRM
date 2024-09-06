package org.green.hr.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContractDTO {
    private Long id;
    private String contractCode;
    private String contractCategory;
    private String contentContract;
    private Float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
    private Long employee_id;
}
