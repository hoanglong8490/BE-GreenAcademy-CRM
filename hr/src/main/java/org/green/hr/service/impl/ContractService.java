package org.green.hr.service.impl;

import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;
import org.green.hr.entity.Employee;
import org.green.hr.repository.ContractRepository;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.service.IContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService implements IContractService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContractDTO> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(contract -> modelMapper.map(contract, ContractDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ContractDTO> getContractById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(c -> modelMapper.map(c, ContractDTO.class));
    }

    @Override
    public ContractDTO saveContract(ContractDTO contractDTO) {
        // Tìm kiếm nhân viên dựa trên employeeCode
        Employee employee = employeeRepository.findEmployeeByCode(contractDTO.getEmployeeCode())
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + contractDTO.getEmployeeCode()));

        // Tạo đối tượng Contract từ ContractDTO
        Contract contract = new Contract();
        contract.setContractCode(contractDTO.getContractCode());
        contract.setContractCategory(contractDTO.getContractCategory());
        contract.setContentContract(contractDTO.getContentContract());
        contract.setSalary(contractDTO.getSalary());
        contract.setDateStart(contractDTO.getDateStart());
        contract.setDateEnd(contractDTO.getDateEnd());
        contract.setStatus(contractDTO.getStatus());
        contract.setCreateAt(contractDTO.getCreateAt());
        contract.setUpdateAt(contractDTO.getUpdateAt());
        contract.setEmployee(employee);

        // Lưu Contract
        Contract savedContract = contractRepository.save(contract);

        // Chuyển Contract về ContractDTO để trả về
        ContractDTO result = new ContractDTO();
        result.setId(savedContract.getId());
        result.setContractCode(savedContract.getContractCode());
        result.setContractCategory(savedContract.getContractCategory());
        result.setContentContract(savedContract.getContentContract());
        result.setSalary(savedContract.getSalary());
        result.setDateStart(savedContract.getDateStart());
        result.setDateEnd(savedContract.getDateEnd());
        result.setStatus(savedContract.getStatus());
        result.setEmployeeCode(savedContract.getEmployee().getEmployeeCode());  // Gán lại mã nhân viên
        result.setCreateAt(savedContract.getCreateAt());
        result.setUpdateAt(savedContract.getUpdateAt());

        return result;
    }

    @Override
    public void deleteContract(Long id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contract not found with id: " + id);
        }
    }
}
