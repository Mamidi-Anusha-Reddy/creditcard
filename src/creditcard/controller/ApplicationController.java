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

  @PostMapping(value = "/applications", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public Application createApplication(
        @RequestPart("application") String applicationJson,
        @RequestPart("idProof") MultipartFile idProof,
        @RequestPart("addressProof") MultipartFile addressProof,
        @RequestPart("incomeProof") MultipartFile incomeProof) throws IOException {

    // Convert JSON string into Application object
    ObjectMapper mapper = new ObjectMapper();
    Application application = mapper.readValue(applicationJson, Application.class);

    // Save files to disk
    String uploadDir = "uploads/";
    File folder = new File(uploadDir);
    if (!folder.exists()) folder.mkdirs();

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
}
