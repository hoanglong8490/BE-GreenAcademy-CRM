package org.green.hr.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDTO {

  Long departmentId;
  String departmentName;
  String description;
  Boolean status;
  Date createAt;
  Date updateAt;

}
