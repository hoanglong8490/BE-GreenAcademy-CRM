package org.green.hr.service;

import org.green.hr.dto.ContractDTO;

import java.util.List;

public interface IContractService {

    List<ContractDTO> getAllContracts();

    ContractDTO getContractById(int id);

    ContractDTO createContract(ContractDTO contractDTO);

    ContractDTO updateContract(int id, ContractDTO contractDTO);

    boolean deleteContract(int id);

    List<ContractDTO> searchContractsByName(String contractName);

    List<ContractDTO> sortContractsByStatus();

    List<ContractDTO> searchByCategoryAndSalary(String contractCategory, Float salary);
}