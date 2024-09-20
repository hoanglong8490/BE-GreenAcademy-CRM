package org.green.hr.converter;

import java.util.Date;
import org.green.hr.dto.WorkProcessDTO;
import org.green.hr.entity.WorkProcess;

public class WorkProcessConverter {
  public  static WorkProcess convertToEntity(WorkProcessDTO workProcessDTO){
    return WorkProcess.builder()
        .id(workProcessDTO.getWorkProcessId())
        .workDate(workProcessDTO.getWorkDate())
        .status(workProcessDTO.getStatus())
        .createAt(workProcessDTO.getCreateAt() != null ? workProcessDTO.getCreateAt() : new Date())
        .updateAt(new Date())
        .build();
  }
  public static WorkProcessDTO convertToDTO(WorkProcess workProcess){
    return WorkProcessDTO.builder()
        .workProcessId(workProcess.getId())
        .workDate(workProcess.getWorkDate())
        .status(workProcess.getStatus())
        .createAt(workProcess.getCreateAt())
        .updateAt(workProcess.getUpdateAt())
        .timeOffId(workProcess.getTimeOff().getId())
        .employeeId(workProcess.getEmployee().getId())
        .rewardDisciplineId(workProcess.getRewardDiscipline().getId())
       .build();
  }
}
