package org.green.hr.controller;

import org.green.core.constant.Constant;
import org.green.core.model.response.CoreResponse;
import org.green.hr.entity.Position;
import org.green.hr.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr/position")
public class PositionController {

    @Autowired
    private IPositionService iPositionService;

    @PostMapping("/create")
    public ResponseEntity<CoreResponse> createPosition(@RequestBody(required = false) Position position) {
        CoreResponse coreResponse = new CoreResponse()
                .setCode(Constant.SUCCESS)
                .setMessage(Constant.SUCCESS_MESSAGE)
                .setData(iPositionService.createPosition(position));

        return ResponseEntity.ok(coreResponse);
    }


}
