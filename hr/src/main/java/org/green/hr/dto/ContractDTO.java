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
<<<<<<< HEAD
=======
    private String contentContract;
>>>>>>> 5fd8feb2834b80f3d7c1e0e56067c9da0135e160
    private Float salary;
    private Date dateStart;
    private Date dateEnd;
    private Short status;
<<<<<<< HEAD
    private String contentContract;
=======
>>>>>>> 5fd8feb2834b80f3d7c1e0e56067c9da0135e160
}
