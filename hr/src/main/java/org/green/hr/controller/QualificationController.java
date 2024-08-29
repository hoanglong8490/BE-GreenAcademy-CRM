package org.green.hr.controller;

import org.green.core.coreApi.CoreController;
import org.green.core.model.response.CoreResponse;
import org.green.hr.entity.Qualification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr")
public class QualificationController implements CoreController {

    @Override
    public CoreResponse create() {
        return null;
    }

    @Override
    public CoreResponse update(int id) {
        return null;
    }

    @Override
    public CoreResponse delete(int id) {
        return null;
    }

    @Override
    public CoreResponse getById(int id) {
        return null;
    }

    @PostMapping("/create")
    public CoreResponse createQualification(@RequestBody(required = false) Qualification qualification) {

        return new CoreResponse();
    }
}
