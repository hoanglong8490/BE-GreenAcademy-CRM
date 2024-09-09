package org.green.hr.controller;


import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.ContractDTO;
import org.green.hr.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @PostMapping
    public ResponseEntity<CoreResponse> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO savedContract = contractService.saveContract(contractDTO);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(savedContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<CoreResponse> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        CoreResponse response = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(null);
        return ResponseEntity.ok(response);
    }
}