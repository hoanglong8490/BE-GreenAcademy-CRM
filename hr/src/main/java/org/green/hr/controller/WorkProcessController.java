package org.green.hr.controller;

import static org.green.core.constant.Constant.SUCCESS;
import static org.green.core.constant.Constant.SUCCESS_MESSAGE;

import jakarta.validation.Valid;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.WorkProcessDTO;
import org.green.hr.service.IWorkProcessService;
import org.green.hr.service.impl.WorkProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr/workprocess")
public class WorkProcessController {

  @Autowired
  private WorkProcessService workProcessService;
  @Autowired
  private IWorkProcessService iWorkProcessService;

  @PostMapping("/addWorkProcess")
  ResponseEntity<CoreResponse> addWorkProcessRequest(
      @RequestBody @Valid WorkProcessDTO workProcessDTO) {
    WorkProcessDTO addWorkProcess = workProcessService.addWorkProcess(workProcessDTO);
    CoreResponse coreResponse = new CoreResponse()
        .setCode(SUCCESS)
        .setMessage(SUCCESS_MESSAGE)
        .setData(addWorkProcess);
    return ResponseEntity.ok(coreResponse);
  }

  @PostMapping("/updateWorkProcess/{id}")
  ResponseEntity<CoreResponse> updateWorkProcess(@PathVariable("id") long id,
     @RequestBody WorkProcessDTO workProcessDTO) {
    WorkProcessDTO updateWorkProcessDTO = workProcessService.updateWorkProcess(id, workProcessDTO);
    CoreResponse coreResponse = new CoreResponse()
        .setCode(SUCCESS)
        .setMessage(SUCCESS_MESSAGE)
        .setData(updateWorkProcessDTO);
    return ResponseEntity.ok(coreResponse);
  }

  @DeleteMapping("/deleteWorkProcess/{id}")
  ResponseEntity<CoreResponse> deleteWorkProcess(@PathVariable("id") long id) {
    WorkProcessDTO deletedWorkProcess = workProcessService.deleteWorkProcess(id);
    CoreResponse coreResponse = new CoreResponse()
        .setCode(SUCCESS)
        .setMessage(SUCCESS_MESSAGE)
        .setData(deletedWorkProcess);
    return ResponseEntity.ok(coreResponse);
  }

  @GetMapping("/getAllWorkProcess")
  ResponseEntity<CoreResponse> getAllWorkProcess() {
    CoreResponse coreResponse = new CoreResponse()
        .setCode(SUCCESS)
        .setMessage(SUCCESS_MESSAGE)
        .setData(iWorkProcessService.getWorkProcess());
    return ResponseEntity.ok(coreResponse);
  }

  @GetMapping("/getWorkProcess/{id}")
  ResponseEntity<CoreResponse> getWorkProcessById(@PathVariable("id") long workProcessId) {
    CoreResponse coreResponse = new CoreResponse()
        .setCode(SUCCESS)
        .setMessage(SUCCESS_MESSAGE)
        .setData(iWorkProcessService.getWorkProcessById(workProcessId));
    return ResponseEntity.ok(coreResponse);
  }
}
