package creditcard.controller;


import com.example.creditcard.model.Application;
import com.example.creditcard.model.Document;
import com.example.creditcard.repository.ApplicationRepository;
import com.example.creditcard.repository.DocumentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final DocumentRepository documentRepository;

    public ApplicationController(ApplicationRepository applicationRepository, DocumentRepository documentRepository) {
        this.applicationRepository = applicationRepository;
        this.documentRepository = documentRepository;
    }

    // Save application
    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get application by ID
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }

    // Save documents
    @PostMapping("/{appId}/documents")
    public Document uploadDocuments(@PathVariable Long appId, @RequestBody Document document) {
        Application application = applicationRepository.findById(appId).orElseThrow();
        document.setApplication(application);
        return documentRepository.save(document);
    }

    // Get documents by application ID
    @GetMapping("/{appId}/documents")
    public Document getDocumentsByApplicationId(@PathVariable Long appId) {
        return documentRepository.findByApplicationId(appId);
    }
}

