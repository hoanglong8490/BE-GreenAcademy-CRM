package org.green.hr.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ContractDTO {
    private int id;
    private String contractCode;
    private String contractCategory;
    private List<String> contentContract;
    private float salary;
    private Instant dateStart;
    private Instant dateEnd;
    private Short status;
    private int employee_id;
}
