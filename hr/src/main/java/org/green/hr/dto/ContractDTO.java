package org.green.hr.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ContractDTO {
    private Long id;
    private String contractCode;
    private String employeeCode;
    private String contractCategory;
    private Float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
    private String contentContract;
}
