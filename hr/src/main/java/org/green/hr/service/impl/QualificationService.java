package org.green.hr.service.impl;

import jakarta.transaction.Transactional;
import org.green.hr.converter.QualificationConverter;
import org.green.hr.dto.QualificationDTO;
import org.green.hr.entity.Qualification;
import org.green.hr.model.response.QualificationResponse;
import org.green.hr.repository.QualificationRepository;
import org.green.hr.service.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationService implements IQualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private QualificationConverter qualificationConverter;

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
}
