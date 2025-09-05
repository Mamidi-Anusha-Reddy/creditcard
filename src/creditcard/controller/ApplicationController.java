package com.scb.creditcardorigination.applicationFormFeature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ObjectMapper objectMapper;

    public ApplicationController(ApplicationService applicationService, ObjectMapper objectMapper) {
        this.applicationService = applicationService;
        this.objectMapper = objectMapper;
    }

    // POST mapping for creating new application
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Application> createApplication(
            @RequestPart("application") String applicationJson,
            @RequestPart("idProof") MultipartFile idProof,
            @RequestPart("addressProof") MultipartFile addressProof,
            @RequestPart("incomeProof") MultipartFile incomeProof
    ) throws IOException {
        // Convert JSON string into Application object
        Application app = objectMapper.readValue(applicationJson, Application.class);

        Application saved = applicationService.saveApplication(app, idProof, addressProof, incomeProof);
        return ResponseEntity.ok(saved);
    }

    // GET mapping for finding all applications
    @GetMapping
    public List<Application> getAll() {
        return applicationService.getAll();
    }

    // GET mapping for finding application by ID
    @GetMapping("/{id}")
    public ResponseEntity<Application> getById(@PathVariable Long id) {
        Application app = applicationService.getById(id);
        return app != null ? ResponseEntity.ok(app) : ResponseEntity.notFound().build();
    }
}
