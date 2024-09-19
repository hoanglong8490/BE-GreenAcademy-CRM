package org.green.hr.converter;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.green.hr.dto.TimeOffDTO;
import org.green.hr.entity.TimeOff;
import org.green.hr.exception.AppException;
import org.green.hr.exception.ErrorCode;

public class TimeOffConverter {
  public static TimeOff convertToEntity(TimeOffDTO timeOffDTO){

    if(timeOffDTO.getDateEnd().before(timeOffDTO.getDateStart())){
      throw new AppException(ErrorCode.DATE_END_BEFORE_DATE_START);
    }

    long diffInMillies = Math.abs(timeOffDTO.getDateEnd().getTime() - timeOffDTO.getDateStart().getTime());
    int dayNumber = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    Short status = timeOffDTO.getStatus();
    if(new Date().after(timeOffDTO.getDateEnd())){
      status =0;
    }
    return TimeOff.builder()
        .id(timeOffDTO.getTimeOffId())
        .dateStart(timeOffDTO.getDateStart())
        .dateEnd(timeOffDTO.getDateEnd())
        .dayNumber(dayNumber)
        .status(status)
        .createAt(new Date())
        .updateAt(new Date())
        .build();
  }
  public static TimeOffDTO convertToDTO(TimeOff timeOff){
    return TimeOffDTO.builder()
        .timeOffId(timeOff.getId())
        .dateStart(timeOff.getDateStart())
        .dateEnd(timeOff.getDateEnd())
        .dayNumber(timeOff.getDayNumber())
        .status(timeOff.getStatus())
        .createAt(timeOff.getCreateAt())
        .updateAt(timeOff.getUpdateAt())
        .employeeId(timeOff.getEmployee().getId())
        .build();
  }
}
