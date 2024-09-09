package org.green.hr.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class ContractDTO {
    private Long id;
    private String contractCode;
    private String employeeCode;
    private String contractCategory;
    private List<String> contentContract;
    private Float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
}
