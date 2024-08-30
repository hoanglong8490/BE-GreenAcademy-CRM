package org.green.hr.service.impl;

import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;
import org.green.hr.entity.Employee;
import org.green.hr.repository.ContractRepository;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService implements IContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO getContractById(int id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ContractDTO createContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        contract.setCreateAt(Instant.now());
        Contract savedContract = contractRepository.save(contract);
        return convertToDTO(savedContract);
    }

    @Override
    public ContractDTO updateContract(int id, ContractDTO contractDTO) {
        Optional<Contract> existingContractOpt = contractRepository.findById(id);
        if (existingContractOpt.isPresent()) {
            Contract existingContract = existingContractOpt.get();
            existingContract.setContractCode(contractDTO.getContractCode());
            existingContract.setContractCategory(contractDTO.getContractCategory());
            existingContract.setContentContract(convertFilesToString(contractDTO.getContentContract())); // Convert list to string
            existingContract.setSalary(contractDTO.getSalary());
            existingContract.setDateStart(contractDTO.getDateStart());
            existingContract.setDateEnd(contractDTO.getDateEnd());
            existingContract.setStatus(contractDTO.getStatus());
            existingContract.setUpdateAt(Instant.now());

            Contract updatedContract = contractRepository.save(existingContract);
            return convertToDTO(updatedContract);
        }
        return null;
    }

    @Override
    public List<ContractDTO> searchByCategoryAndSalary(String contractCategory, Float salary) {
        List<Contract> contracts = contractRepository.findByContractCategoryAndSalary(contractCategory, salary);
        return contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteContract(int id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (contract.isPresent()) {
            contractRepository.markAsDeleted(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ContractDTO> searchContractsByName(String contractName) {
        List<Contract> contracts = contractRepository.findByContractName("%" + contractName + "%");
        return contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContractDTO> sortContractsByStatus() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .sorted(Comparator.comparing(Contract::getStatus))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ContractDTO convertToDTO(Contract contract) {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setContractCode(contract.getContractCode());
        contractDTO.setContractCategory(contract.getContractCategory());
        contractDTO.setContentContract(contract.getContentContractFiles()); // Convert string to list
        contractDTO.setSalary(contract.getSalary());
        contractDTO.setDateStart(contract.getDateStart());
        contractDTO.setDateEnd(contract.getDateEnd());
        contractDTO.setStatus(contract.getStatus());
        contractDTO.setEmployee_id(contract.getEmployee().getId());
        return contractDTO;
    }

    private Contract convertToEntity(ContractDTO contractDTO) {
        Contract contract = new Contract();
        contract.setContractCode(contractDTO.getContractCode());
        contract.setContractCategory(contractDTO.getContractCategory());
        contract.setContentContractFiles(contractDTO.getContentContract()); // Convert list to string
        contract.setSalary(contractDTO.getSalary());
        contract.setDateStart(contractDTO.getDateStart());
        contract.setDateEnd(contractDTO.getDateEnd());
        contract.setStatus(contractDTO.getStatus());
        Employee employee = employeeRepository.findById(contractDTO.getEmployee_id()).orElse(null);
        contract.setEmployee(employee);
        return contract;
    }

    // Chuyển File thành String cách nhau bởi dấu ","
    private String convertFilesToString(List<String> files) {
        return files == null ? null : String.join(",", files);
    }
}
