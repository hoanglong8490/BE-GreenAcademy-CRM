package org.green.hr.converter;

import org.green.hr.dto.AllowanceDTO;
import org.green.hr.entity.Allowance;
import org.green.hr.model.response.AllowanceResponse;
import org.green.hr.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllowanceConverter {
	
	@Autowired
	private PositionRepository positionRepository;

	public Allowance convertToEntity(AllowanceDTO allowanceDTO) {
		
		Allowance allowance = Allowance.builder()
				.id(allowanceDTO.getId())
				.allowanceCategory(allowanceDTO.getAllowanceCategory())
				.allowanceSalary(allowanceDTO.getAllowanceSalary())
				.status(allowanceDTO.getStatus())
				.position(positionRepository.findById(allowanceDTO.getPositionId()).orElse(null))
				.build();
		
		return allowance;
	}
	
	public AllowanceResponse convertToResponse(Allowance allowance) {
		AllowanceResponse allowanceResponse = AllowanceResponse.builder()
				.id(allowance.getId())
				.allowanceCategory(allowance.getAllowanceCategory())
				.allowanceSalary(allowance.getAllowanceSalary())
				.status(allowance.getStatus())
				.positionId(allowance.getPosition().getId())
				.positionName(allowance.getPosition().getPositionName())
				.createAt(allowance.getCreateAt())
				.updateAt(allowance.getUpdateAt())
				.build();
		
		return allowanceResponse;
	}
}
