package org.green.hr.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class QualificationDTO {
    private Long id;
    private String qualificationName;
    private Long employeeId;
    private Date expiryDate;
    private String imagePath;
    private Short status;
    private Date createAt;
    private Date updateAt;
}
