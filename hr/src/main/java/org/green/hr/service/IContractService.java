package org.green.hr.service;

import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {
    Page<ContractDTO> getAllContracts(Pageable pageable);
    Optional<ContractDTO> getContractById(Long id);
    ContractDTO createContract(ContractDTO contractDTO);
    ContractDTO saveContract(ContractDTO contractDTO);
    void updateContractStatus(Long id, boolean status);

    // Phương thức search với phân trang
    Page<ContractDTO> searchContracts(String searchTerm, String contractCategory, Double minSalary, Double maxSalary, Pageable pageable);


}