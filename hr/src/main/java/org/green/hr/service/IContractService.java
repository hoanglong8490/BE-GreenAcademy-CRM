package org.green.hr.service;

import org.green.hr.dto.ContractDTO;

import java.util.List;
import java.util.Optional;

public interface IContractService {
    List<ContractDTO> getAllContracts();

    Optional<ContractDTO> getContractById(Long id);

    ContractDTO saveContract(ContractDTO contractDTO);

    void deleteContract(Long id);

}