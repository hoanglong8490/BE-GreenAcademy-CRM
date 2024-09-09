package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.QualificationDTO;
import org.green.hr.service.impl.QualificationService;
import org.green.hr.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hr/qualifications")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private UploadFile uploadFile;

    @GetMapping
    public ResponseEntity<CoreResponse> getAllQualifications(@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                             @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(this.qualificationService.getAllQualifications(pageNo - 1, pageSize));

        return ResponseEntity.ok().body(coreResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<CoreResponse> handleCreateQualification(@RequestBody(required = false) QualificationDTO qualificationDTO, @RequestParam("image") MultipartFile multipartFile) {

        qualificationDTO.setImage(this.uploadFile.uploadFile(multipartFile));

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(this.qualificationService.handldeSaveQualification(qualificationDTO));

        return ResponseEntity.ok().body(coreResponse);
    }
}
