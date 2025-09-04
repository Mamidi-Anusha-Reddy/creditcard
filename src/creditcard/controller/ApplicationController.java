package com.scb.creditcardorigination.applicationFormFeature.controller;

import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Application> createApplication(
            @RequestPart String fullName,
            @RequestPart String phoneNumber,
            @RequestPart String creditCardType,
            @RequestPart String profileType,
            @RequestPart MultipartFile idProof,
            @RequestPart MultipartFile addressProof,
            @RequestPart MultipartFile incomeProof
    ) throws IOException {
        Application app = new Application();
        app.setFullName(fullName);
        app.setPhoneNumber(phoneNumber);
        app.setCreditCardType(creditCardType);
        app.setProfileType(profileType);

        Application saved = applicationService.saveApplication(app, idProof, addressProof, incomeProof);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Application> getAll() {
        return applicationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getById(@PathVariable Long id) {
        Application app = applicationService.getById(id);
        return app != null ? ResponseEntity.ok(app) : ResponseEntity.notFound().build();
    }
}
