package org.green.hr.converter;

import org.green.hr.dto.QualificationDTO;
import org.green.hr.entity.Qualification;
import org.green.hr.model.response.QualificationResponse;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class QualificationConverter {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UploadFile uploadFile;

    public Qualification convertToEntity(QualificationDTO qualificationDTO) {
        Qualification qualification = new Qualification();

        qualification.setId(qualificationDTO.getId());
        qualification.setQualificationName(qualificationDTO.getQualificationName());
        qualification.setImage(qualificationDTO.getImagePath());
        qualification.setStatus(qualificationDTO.getStatus());
        qualification.setExpiryDate(qualificationDTO.getExpiryDate());
        qualification.setCreateAt(new Date());
        qualification.setEmployee(this.employeeRepository.getReferenceById(qualificationDTO.getEmployeeId()));

        return qualification;
    }

    public QualificationResponse convertToResponse(Qualification qualification) {
        QualificationResponse qualificationResponse = new QualificationResponse();

        qualificationResponse.setId(qualification.getId());
        qualificationResponse.setQualificationName(qualification.getQualificationName());
        qualificationResponse.setEmployeeName(qualification.getEmployee().getEmployeeName());
        qualificationResponse.setExpiryDate(qualification.getExpiryDate());
        qualificationResponse.setStatus(qualification.getStatus());

        return qualificationResponse;
    }
}
