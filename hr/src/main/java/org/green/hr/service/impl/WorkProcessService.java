package org.green.hr.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.green.hr.converter.WorkProcessConverter;
import org.green.hr.dto.WorkProcessDTO;
import org.green.hr.entity.Employee;
import org.green.hr.entity.RewardDiscipline;
import org.green.hr.entity.TimeOff;
import org.green.hr.entity.WorkProcess;
import org.green.hr.exception.AppException;
import org.green.hr.exception.ErrorCode;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.repository.RewardDisciplineRepository;
import org.green.hr.repository.TimeOffRepository;
import org.green.hr.repository.WorkProcessRepository;
import org.green.hr.service.IWorkProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkProcessService implements IWorkProcessService {

  @Autowired
  WorkProcessRepository workProcessRepository;
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  TimeOffRepository timeOffRepository;
  @Autowired
  RewardDisciplineRepository rewardDisciplineRepository;

  @Override
  public WorkProcessDTO addWorkProcess(WorkProcessDTO workProcessDTO) {
    Employee employee = employeeRepository.findById(workProcessDTO.getEmployeeId()).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
    TimeOff timeOff = timeOffRepository.findById(workProcessDTO.getTimeOffId()).orElseThrow(() -> new AppException(ErrorCode.TIME_OFF_NOT_FOUND));
    RewardDiscipline rewardDiscipline = rewardDisciplineRepository.findById(workProcessDTO.getRewardDisciplineId()).orElseThrow(() -> new AppException(ErrorCode.REWARD_DISCIPLINE_NOT_FOUND));
    WorkProcess workProcess = WorkProcessConverter.convertToEntity(workProcessDTO);
    workProcess.setEmployee(employee);
    workProcess.setTimeOff(timeOff);
    workProcess.setRewardDiscipline(rewardDiscipline);
    WorkProcess saveWorkProcess = workProcessRepository.save(workProcess);
    return WorkProcessConverter.convertToDTO(saveWorkProcess);
  }

  @Override
  public WorkProcessDTO updateWorkProcess(long id, WorkProcessDTO workProcessDTO) {
    WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.WORK_PROCESS_NOT_FOUND));
    workProcess.setWorkDate(workProcessDTO.getWorkDate());
    workProcess.setStatus(workProcessDTO.getStatus());
    workProcess.setUpdateAt(new Date());
    WorkProcess updateWorkProcess = workProcessRepository.save(workProcess);
    return WorkProcessConverter.convertToDTO(updateWorkProcess);
  }

  @Override
  public WorkProcessDTO deleteWorkProcess(long id) {
    WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.WORK_PROCESS_NOT_FOUND));
    workProcessRepository.delete(workProcess);
    return WorkProcessConverter.convertToDTO(workProcess);
  }

  @Override
  public List<WorkProcessDTO> getWorkProcess() {
    List<WorkProcess> workProcess = workProcessRepository.findAll();
    workProcess.sort(Comparator.comparingLong(WorkProcess::getId).reversed());

    return workProcess.stream().map(WorkProcessConverter::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public List<WorkProcessDTO> getWorkProcessById(long id) {
    WorkProcess workProcess = workProcessRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.WORK_PROCESS_NOT_FOUND));
    return List.of(WorkProcessConverter.convertToDTO(workProcess));
  }
}
