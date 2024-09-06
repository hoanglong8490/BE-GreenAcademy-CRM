package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.ContractDTO;
import org.green.hr.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hr/contracts")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @GetMapping
    public ResponseEntity<CoreResponse> getAllContracts(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<ContractDTO> contracts = contractService.getAllContracts();
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(contracts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoreResponse> getContractById(@PathVariable int id) {
        ContractDTO contract = contractService.getContractById(id);
        CoreResponse response = new CoreResponse();
        if (contract != null) {
            response.setCode(Constant.SUCCESS)
                    .setMessage("Contract found")
                    .setData(contract);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(Constant.NOT_FOUND)
                    .setMessage(Constant.NOT_FOUND_MESSAGE);
            return ResponseEntity.status(Constant.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CoreResponse> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO createdContract = contractService.createContract(contractDTO);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(createdContract);
        return ResponseEntity.status(Constant.SUCCESS).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoreResponse> updateContract(@PathVariable int id, @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        CoreResponse response = new CoreResponse();
        if (updatedContract != null) {
            response.setCode(Constant.SUCCESS)
                    .setMessage("Contract updated successfully")
                    .setData(updatedContract);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(Constant.NOT_FOUND)
                    .setMessage(Constant.NOT_FOUND_MESSAGE);
            return ResponseEntity.status(Constant.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CoreResponse> deleteContract(@PathVariable int id) {
        boolean isDeleted = contractService.deleteContract(id);
        CoreResponse response = new CoreResponse();
        if (isDeleted) {
            response.setCode(Constant.NO_CONTENT)
                    .setMessage(Constant.NO_CONTENT_MESSAGE);
            return ResponseEntity.noContent().build();
        } else {
            response.setCode(Constant.NOT_FOUND)
                    .setMessage(Constant.NOT_FOUND_MESSAGE);
            return ResponseEntity.status(Constant.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CoreResponse> searchContractsByName(@RequestParam String name) {
        List<ContractDTO> contracts = contractService.searchContractsByName(name);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(contracts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sort/status")
    public ResponseEntity<CoreResponse> sortContractsByStatus() {
        List<ContractDTO> sortedContracts = contractService.sortContractsByStatus();
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(sortedContracts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/category")
    public ResponseEntity<CoreResponse> searchByCategoryAndSalary(@RequestParam String contractCategory, @RequestParam Float salary) {
        List<ContractDTO> contracts = contractService.searchByCategoryAndSalary(contractCategory, salary);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(contracts);
        return ResponseEntity.ok(response);
    }
}