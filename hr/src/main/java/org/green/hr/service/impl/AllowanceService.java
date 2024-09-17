package org.green.hr.service.impl;

import java.util.Date;

import org.green.hr.converter.AllowanceConverter;
import org.green.hr.dto.AllowanceDTO;
import org.green.hr.entity.Allowance;
import org.green.hr.model.request.AllowanceSearch;
import org.green.hr.model.response.AllowanceResponse;
import org.green.hr.repository.AllowanceRepository;
import org.green.hr.service.IAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AllowanceService implements IAllowanceService {
	
	@Autowired
	private AllowanceRepository allowanceRepository;
	
	@Autowired
	private AllowanceConverter allowanceConverter;

	@Override
	public Page<AllowanceResponse> filterAllwance(int pageNo, int pageSize, AllowanceSearch allowanceSearch) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		Page<Allowance> allowances = this.allowanceRepository.findByStatusAndKeyword(allowanceSearch.getStatus(), allowanceSearch.getKeyword(), pageable);
		
		return allowances.map(item -> this.allowanceConverter.convertToResponse(item));
	}

	@Override
	public AllowanceResponse getById(Long id) {
		Allowance allowance = this.allowanceRepository.findById(id).orElse(null);
		return this.allowanceConverter.convertToResponse(allowance);
	}

	@Transactional
	@Override
	public AllowanceResponse handleCreateAllowance(AllowanceDTO allowanceDTO) {
		Allowance allowance = this.allowanceConverter.convertToEntity(allowanceDTO);
		allowance.setCreateAt(new Date());
		
		Allowance newAllowance = this.allowanceRepository.save(allowance);
		return this.allowanceConverter.convertToResponse(newAllowance);
	}

	@Transactional
	@Override
	public AllowanceResponse handleUpdateAllowance(AllowanceDTO allowanceDTO) {
		Allowance allowance = this.allowanceConverter.convertToEntity(allowanceDTO);
		allowance.setUpdateAt(new Date());
		
		Allowance updateAllowance = this.allowanceRepository.save(allowance);
		
		return this.allowanceConverter.convertToResponse(updateAllowance);
	}

	@Transactional
	@Override
	public AllowanceResponse handleDeleteAllowance(AllowanceDTO allowanceDTO) {
		Allowance allowance = this.allowanceConverter.convertToEntity(allowanceDTO);
		
		Allowance updateAllowance = this.allowanceRepository.save(allowance);
		
		return this.allowanceConverter.convertToResponse(updateAllowance);
	}
}
