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
    private List<String> contentContract;
    private float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
    private Long employee_id;
}
