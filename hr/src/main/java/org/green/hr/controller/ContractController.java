package org.green.hr.controller;


import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.ContractDTO;
import org.green.hr.entity.Contract;
import org.green.hr.exception.ResourceNotFoundException;
import org.green.hr.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping
    public ResponseEntity<CoreResponse> getAllContracts(
            @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        if (pageNo < 1) {
            pageNo = 1;
        }

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ContractDTO> result;
        try {
            result = contractService.getAllContracts(pageable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi lấy danh sách hợp đồng")
                            .setData(null));
        }

        Map<String, Object> responseData = Map.of(
                "contracts", result.getContent(),
                "totalElements", result.getTotalElements(),
                "totalPages", result.getTotalPages(),
                "currentPage", result.getNumber() + 1,
                "pageSize", result.getSize()
        );

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(responseData);

        return ResponseEntity.ok(coreResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoreResponse> getContractById(@PathVariable Long id) {
        try {
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
                        .setMessage("Không tìm thấy hợp đồng với ID: " + id)
                        .setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi lấy hợp đồng")
                            .setData(null));
        }
    }


    @PostMapping("/")
    public ResponseEntity<CoreResponse> createContract(@RequestBody ContractDTO contractDTO) {
        try {
            ContractDTO createdContract = contractService.createContract(contractDTO);
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.SUCCESS)
                    .setMessage(Constant.SUCCESS_MESSAGE)
                    .setData(createdContract);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi tạo hợp đồng")
                            .setData(null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoreResponse> updateContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        contractDTO.setId(id);
        try {
            ContractDTO updatedContract = contractService.saveContract(contractDTO);
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.SUCCESS)
                    .setMessage(Constant.SUCCESS_MESSAGE)
                    .setData(updatedContract);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi cập nhật hợp đồng")
                            .setData(null));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CoreResponse> updateContractStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {
        Boolean status = (Boolean) payload.get("status");
        if (status == null) {
            return ResponseEntity.badRequest().body(new CoreResponse()
                    .setCode(Constant.BAD_REQUEST)
                    .setMessage("Trạng thái không hợp lệ")
                    .setData(null));
        }

        try {
            contractService.updateContractStatus(id, status);
            CoreResponse response = new CoreResponse()
                    .setCode(Constant.SUCCESS)
                    .setMessage("Cập nhật trạng thái thành công")
                    .setData(null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi cập nhật trạng thái hợp đồng")
                            .setData(null));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CoreResponse> searchContracts(
            @RequestParam(name = "searchTerm", required = false) String searchTerm,
            @RequestParam(name = "contractCategory", required = false) String contractCategory,
            @RequestParam(name = "minSalary", required = false) Double minSalary,
            @RequestParam(name = "maxSalary", required = false) Double maxSalary,
            @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        if (pageNo < 1) {
            pageNo = 1;
        }

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ContractDTO> result;
        try {
            result = contractService.searchContracts(searchTerm, contractCategory, minSalary, maxSalary, pageable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CoreResponse()
                            .setCode(Constant.INTERNAL_SERVER_ERROR)
                            .setMessage("Có lỗi xảy ra khi tìm kiếm hợp đồng")
                            .setData(null));
        }

        Map<String, Object> responseData = Map.of(
                "contracts", result.getContent(),
                "totalElements", result.getTotalElements(),
                "totalPages", result.getTotalPages(),
                "currentPage", result.getNumber() + 1,
                "pageSize", result.getSize()
        );

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Tìm kiếm hợp đồng thành công")
                .setData(responseData);

        return ResponseEntity.ok(coreResponse);
    }
}
