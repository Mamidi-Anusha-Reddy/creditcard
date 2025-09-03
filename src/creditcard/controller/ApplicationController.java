package com.example.creditcard.controller;

import com.example.creditcard.model.Application;
import com.example.creditcard.model.Document;
import com.example.creditcard.repository.ApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // ✅ POST
    @PostMapping(value = "/applications", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Application createApplication(
            @RequestPart("application") String applicationJson,
            @RequestPart("idProof") MultipartFile idProof,
            @RequestPart("addressProof") MultipartFile addressProof,
            @RequestPart("incomeProof") MultipartFile incomeProof
    ) throws IOException {

        // Convert JSON string into Application object
        ObjectMapper mapper = new ObjectMapper();
        Application application = mapper.readValue(applicationJson, Application.class);

        // Create uploads directory
        String uploadDir = "uploads/";
        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        // Save files
        String idProofPath = uploadDir + idProof.getOriginalFilename();
        idProof.transferTo(new File(idProofPath));

        String addressProofPath = uploadDir + addressProof.getOriginalFilename();
        addressProof.transferTo(new File(addressProofPath));

        String incomeProofPath = uploadDir + incomeProof.getOriginalFilename();
        incomeProof.transferTo(new File(incomeProofPath));

        // Link documents
        Document documents = new Document();
        documents.setIdProofPath(idProofPath);
        documents.setAddressProofPath(addressProofPath);
        documents.setIncomeProofPath(incomeProofPath);
        documents.setApplication(application);

        application.setDocuments(documents);

        return applicationRepository.save(application);
    }

    // ✅ GET
    @GetMapping("/applications/{id}")
    public Application getApplication(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id " + id));
    }
}
