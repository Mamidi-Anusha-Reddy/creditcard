// src/main/java/com/scb/creditcardorigination/applicationFormFeature/controller/ApplicationController.java
package com.scb.creditcardorigination.applicationFormFeature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.service.ApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000") // or use @CrossOrigin on specific origins as needed
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ObjectMapper objectMapper;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public ApplicationController(ApplicationService applicationService, ObjectMapper objectMapper) {
        this.applicationService = applicationService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Application> createApplication(
            @RequestPart("application") String applicationJson,
            @RequestPart("idProof") MultipartFile idProof,
            @RequestPart("addressProof") MultipartFile addressProof,
            @RequestPart("incomeProof") MultipartFile incomeProof
    ) throws IOException {
        Application app = objectMapper.readValue(applicationJson, Application.class);
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

    @GetMapping("/documents/{fileName:.+}")
    public ResponseEntity<Resource> getDocument(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
