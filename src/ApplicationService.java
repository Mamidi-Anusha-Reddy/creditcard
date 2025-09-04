package com.scb.creditcardorigination.applicationFormFeature.service;

import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.model.Document;
import com.scb.creditcardorigination.applicationFormFeature.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

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

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        Document doc = new Document();
        doc.setIdProofPath(storeFile(uploadPath, idProof));
        doc.setAddressProofPath(storeFile(uploadPath, addressProof));
        doc.setIncomeProofPath(storeFile(uploadPath, incomeProof));

        application.setDocuments(doc);
        return applicationRepository.save(application);
    }

    private String storeFile(Path uploadPath, MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path target = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return target.toString();
    }

    public java.util.List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }
}
