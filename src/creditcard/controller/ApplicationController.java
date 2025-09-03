package com.scb.creditcardorigination.controller;

import com.scb.creditcardorigination.model.Application;
import com.scb.creditcardorigination.model.Document;
import com.scb.creditcardorigination.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000") // for React
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Application createApplication(
            @RequestPart("application") Application application,
            @RequestPart("idProof") MultipartFile idProof,
            @RequestPart("addressProof") MultipartFile addressProof,
            @RequestPart("incomeProof") MultipartFile incomeProof) throws IOException {

        // Create upload folder if not exists
        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // Save files
        String idProofPath = UPLOAD_DIR + idProof.getOriginalFilename();
        idProof.transferTo(new File(idProofPath));

        String addressProofPath = UPLOAD_DIR + addressProof.getOriginalFilename();
        addressProof.transferTo(new File(addressProofPath));

        String incomeProofPath = UPLOAD_DIR + incomeProof.getOriginalFilename();
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

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Iterable<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}
