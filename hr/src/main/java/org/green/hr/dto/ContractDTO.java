package org.green.hr.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
public class ContractDTO {
    private Long id;
    private String contractCode;
    private String contractCategory;
    private String contentContract;
    private float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
    private Date createAt;
    private Date updateAt;
    //    private Long employeeId;
    private String employeeCode;
}
