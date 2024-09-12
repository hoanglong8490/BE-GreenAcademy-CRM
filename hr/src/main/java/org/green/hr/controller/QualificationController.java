package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.dto.QualificationDTO;
import org.green.hr.model.request.QualificationSearch;
import org.green.hr.service.impl.QualificationService;
import org.green.hr.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hr/qualifications")
@CrossOrigin
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

    @GetMapping("/search")
    public ResponseEntity<CoreResponse> searchQualifications(@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                             @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                             @RequestBody(required = false) QualificationSearch qualificationSearch) {
        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Filter success")
                .setData(this.qualificationService.filterQualification(pageNo, pageSize, qualificationSearch));
        return ResponseEntity.ok().body(coreResponse);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CoreResponse> handleCreateQualification(
            @RequestPart(value = "qualificationDTO", required = false) QualificationDTO qualificationDTO,
            @RequestPart(value = "image", required = false) MultipartFile multipartFile) {

        if (multipartFile != null) {
            qualificationDTO.setImagePath(uploadFile.uploadFile(multipartFile));
        }

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(this.qualificationService.handldeSaveQualification(qualificationDTO));

        return ResponseEntity.ok().body(coreResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CoreResponse> getQualificationById(@PathVariable("id") Long id) {
        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Qualification found")
                .setData(this.qualificationService.getQualificationById(id));

        return ResponseEntity.ok().body(coreResponse);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CoreResponse> updateQualification(@PathVariable("id") Long id,
                                                            @RequestPart(value = "qualificationDTO", required = false) QualificationDTO qualificationDTO,
                                                            @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        if (multipartFile != null) qualificationDTO.setImagePath(uploadFile.uploadFile(multipartFile));

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Qualification updated")
                .setData(this.qualificationService.updateQualification(qualificationDTO, id));

        return ResponseEntity.ok().body(coreResponse);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<CoreResponse> deleteQualification(@PathVariable("id") Long id) {

        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage("Qualification deleted")
                .setData(this.qualificationService.deleteQualification(id));

        return ResponseEntity.ok().body(coreResponse);
    }
}
