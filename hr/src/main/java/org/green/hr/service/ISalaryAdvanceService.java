package org.green.hr.service;

import java.util.List;
import org.green.hr.dto.SalaryAdvanceDTO;

public interface ISalaryAdvanceService {

  SalaryAdvanceDTO addSalaryAdvance(SalaryAdvanceDTO salaryAdvanceDTO);

  SalaryAdvanceDTO updateSalaryAdvance(long salaryAdvanceId, SalaryAdvanceDTO salaryAdvanceDTO);

  SalaryAdvanceDTO deleteSalaryAdvance(long salaryAdvanceId);

  List<SalaryAdvanceDTO> getSalaryAdvances();

  List<SalaryAdvanceDTO> getSalaryAdvanceById(long salaryAdvanceId);
}
