package org.green.hr.service.impl;

import jakarta.transaction.Transactional;
import org.green.hr.converter.QualificationConverter;
import org.green.hr.dto.QualificationDTO;
import org.green.hr.entity.Qualification;
import org.green.hr.model.request.QualificationSearch;
import org.green.hr.model.response.QualificationResponse;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.repository.QualificationRepository;
import org.green.hr.service.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QualificationService implements IQualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private QualificationConverter qualificationConverter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public QualificationDTO handldeSaveQualification(QualificationDTO qualificationDTO) {
        Qualification qualification = this.qualificationConverter.convertToEntity(qualificationDTO);
        this.qualificationRepository.save(qualification);
        return qualificationDTO;
    }

    @Override
    public Page<QualificationResponse> getAllQualifications(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Qualification> qualifications = this.qualificationRepository.findAll(pageable);

        return qualifications.map((qualification) -> {
            return this.qualificationConverter.convertToResponse(qualification);
        });
    }

    @Override
    public QualificationDTO getQualificationById(Long id) {
        return this.qualificationConverter.convertToDTO(this.qualificationRepository.findById(id).get());
    }

    @Transactional
    @Override
    public QualificationResponse updateQualification(QualificationDTO qualificationDTO, Long id) {

        Qualification qualification = this.qualificationRepository.findById(id).orElse(null);

        if (qualification != null) {
            qualification.setQualificationName(qualificationDTO.getQualificationName());
            qualification.setEmployee(this.employeeRepository.findById(qualificationDTO.getEmployeeId()).orElse(null));
            if(qualificationDTO.getImagePath() != null || !qualificationDTO.getImagePath().isEmpty()) qualification.setImage(qualificationDTO.getImagePath());
            qualification.setStatus(qualificationDTO.getStatus());
            qualification.setExpiryDate(qualificationDTO.getExpiryDate());
            qualification.setUpdateAt(new Date());
        }

        assert qualification != null : "Qualification cannot be null";
        return qualificationConverter.convertToResponse(this.qualificationRepository.saveAndFlush(qualification));
    }

    @Override
    public QualificationResponse searchQualifications(int pageNo, int pageSize, QualificationSearch qualificationSearch) {
        return null;
    }

    @Transactional
    @Override
    public QualificationResponse deleteQualification(Long id) {

        Qualification qualification = this.qualificationRepository.findById(id).orElse(null);

        if(qualification != null) qualification.setStatus((short) 0);

        assert qualification != null;
        return this.qualificationConverter.convertToResponse(this.qualificationRepository.saveAndFlush(qualification));
    }
}
