package org.green.hr.service.impl;

import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;
import org.green.hr.entity.Employee;
import org.green.hr.exception.ResourceNotFoundException;
import org.green.hr.repository.ContractRepository;
import org.green.hr.repository.EmployeeRepository;
import org.green.hr.service.IContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
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
    public Page<ContractDTO> getAllContracts(Pageable pageable) {
        Page<Contract> contractsPage = contractRepository.findAll(pageable);
        return contractsPage.map(contract -> modelMapper.map(contract, ContractDTO.class));
    }

    @Override
    public Optional<ContractDTO> getContractById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(c -> modelMapper.map(c, ContractDTO.class));
    }

    @Override
    public ContractDTO createContract(ContractDTO contractDTO) {
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
        contract.setCreateAt(new Date()); // Cập nhật thời gian tạo mới
        contract.setUpdateAt(new Date()); // Cập nhật thời gian tạo mới
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
    public ContractDTO saveContract(ContractDTO contractDTO) {
        // Tìm kiếm nhân viên dựa trên employeeCode
        Employee employee = employeeRepository.findEmployeeByCode(contractDTO.getEmployeeCode())
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + contractDTO.getEmployeeCode()));

        // Tìm kiếm hợp đồng dựa trên ID (nếu có)
        Contract contract;
        if (contractDTO.getId() != null) {
            contract = contractRepository.findById(contractDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Contract not found with id: " + contractDTO.getId()));
        } else {
            contract = new Contract();  // Nếu không có ID, thì tạo hợp đồng mới
        }

        // Cập nhật thông tin hợp đồng
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
        result.setEmployeeCode(savedContract.getEmployee().getEmployeeCode());
        result.setCreateAt(savedContract.getCreateAt());
        result.setUpdateAt(savedContract.getUpdateAt());

        return result;
    }

    @Override
    public void updateContractStatus(Long id, boolean status) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            contract.setStatus((short) (status ? 1 : 0));
            contractRepository.save(contract);
        } else {
            throw new ResourceNotFoundException("Contract not found with id " + id);
        }
    }

    @Override
    public Page<ContractDTO> searchContracts(String searchTerm, String contractCategory, Double minSalary, Double maxSalary, Pageable pageable) {
        // Điều chỉnh searchTerm nếu không rỗng
        if (searchTerm != null && !searchTerm.isEmpty()) {
            searchTerm = "%" + searchTerm.toLowerCase() + "%";
        } else {
            searchTerm = "%";
        }

        // Nếu contractCategory là null, thì không lọc theo contractCategory
        if (contractCategory == null) {
            contractCategory = "%";
        }

        Page<Contract> contractsPage = contractRepository.findBySearchTermAndTypeAndSalaryBetween(
                searchTerm,
                contractCategory,
                minSalary != null ? minSalary : 0.0,
                maxSalary != null ? maxSalary : Double.MAX_VALUE,
                pageable
        );

        return contractsPage.map(contract -> modelMapper.map(contract, ContractDTO.class));
    }




}
