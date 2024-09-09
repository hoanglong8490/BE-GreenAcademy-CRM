package org.green.hr.service;

import org.green.hr.dto.QualificationDTO;
import org.green.hr.model.response.QualificationResponse;
import org.springframework.data.domain.Page;


public interface IQualificationService {

    QualificationDTO handldeSaveQualification(QualificationDTO qualificationDTO);

    Page<QualificationResponse> getAllQualifications(int pageNo, int pageSize);
}
