package org.green.hr.controller;


import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;
import org.green.hr.exception.ResourceNotFoundException;
import org.green.hr.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hr/contracts")
public class ContractController {
    @Autowired
    private IContractService contractService;

    // Lấy danh sách Contract
    @GetMapping
    public ResponseEntity<CoreResponse> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(contracts);
        return ResponseEntity.ok(response);
    }

    // Lấy Contract theo id
    @GetMapping("/{id}")
    public ResponseEntity<CoreResponse> getContractById(@PathVariable Long id) {
        Optional<ContractDTO> contract = contractService.getContractById(id);
        if (contract.isPresent()) {
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.SUCCESS)
                    .setMessage(Constant.SUCCESS_MESSAGE)
                    .setData(contract.get());
            return ResponseEntity.ok(response);
        } else {
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.NOT_FOUND)
                    .setMessage(Constant.NOT_FOUND_MESSAGE)
                    .setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Tạo mới contract
    @PostMapping("/")
    public ResponseEntity<CoreResponse> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO createdContract = contractService.createContract(contractDTO);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(createdContract);
        return ResponseEntity.ok(response);
    }


    // Update Contract
    @PutMapping("/{id}")
    public ResponseEntity<CoreResponse> updateContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        contractDTO.setId(id);
        ContractDTO updatedContract = contractService.saveContract(contractDTO);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(updatedContract);
        return ResponseEntity.ok(response);
    }


    // Cập nhật status Contract (Xóa Contract)
    @PutMapping("/{id}/status")
    public ResponseEntity<CoreResponse> updateContractStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {
        Boolean status = (Boolean) payload.get("status");
        if (status == null) {
            return ResponseEntity.badRequest().body(new CoreResponse()
                    .setCode(Constant.INTERNAL_SERVER_ERROR)
                    .setMessage("Trạng thái không hợp lệ")
                    .setData(null));
        }

        contractService.updateContractStatus(id, status);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Cập nhật trạng thái thành công")
                .setData(null);
        return ResponseEntity.ok(response);
    }


    // Tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<CoreResponse> searchContracts(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String contractCategory,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            Pageable pageable) {

        try {
            Page<Contract> result = contractService.searchContracts(searchTerm, contractCategory, minSalary, maxSalary, pageable);
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.SUCCESS)
                    .setMessage("Tìm kiếm hợp đồng thành công")
                    .setData(result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.INTERNAL_SERVER_ERROR)
                    .setMessage("Lỗi khi tìm kiếm hợp đồng: " + e.getMessage())
                    .setData(null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}