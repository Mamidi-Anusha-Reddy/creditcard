package com.scb.creditcardorigination.applicationFormFeature.service;

import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.model.Document;
import com.scb.creditcardorigination.applicationFormFeature.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application saveApplication(Application application,
                                       MultipartFile idProof,
                                       MultipartFile addressProof,
                                       MultipartFile incomeProof) throws IOException {

        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Create document entity and store file paths
        Document doc = new Document();
        doc.setIdProofPath(storeFile(uploadPath, idProof));
        doc.setAddressProofPath(storeFile(uploadPath, addressProof));
        doc.setIncomeProofPath(storeFile(uploadPath, incomeProof));

        // Associate document with application
        application.setDocuments(doc);

        // Save and return the application
        return applicationRepository.save(application);
    }

    private String storeFile(Path uploadPath, MultipartFile file) throws IOException {
        // Generate unique filename
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path target = uploadPath.resolve(filename);
        
        // Copy file to target location
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        
        return filename; // Return relative filename for database storage
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Application> getByEmail(String email) {
        return applicationRepository.findByEmail(email);
    }

    public List<Application> getByCreditCardType(String creditCardType) {
        return applicationRepository.findByCreditCardType(creditCardType);
    }

    public List<Application> getByProfileType(String profileType) {
        return applicationRepository.findByProfileType(profileType);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public Application updateApplication(Long id, Application updatedApplication) {
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setFullName(updatedApplication.getFullName());
                    application.setPhoneNumber(updatedApplication.getPhoneNumber());
                    application.setEmail(updatedApplication.getEmail());
                    application.setCreditCardType(updatedApplication.getCreditCardType());
                    application.setProfileType(updatedApplication.getProfileType());
                    return applicationRepository.save(application);
                }).orElse(null);
    }
}
