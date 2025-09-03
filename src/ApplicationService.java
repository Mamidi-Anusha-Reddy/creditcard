package creditcard.service;


import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import com.scb.creditcardorigination.applicationFormFeature.model.Document;
import com.scb.creditcardorigination.applicationFormFeature.repository.ApplicationRepository;
import com.scb.creditcardorigination.applicationFormFeature.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final DocumentRepository documentRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              DocumentRepository documentRepository) {
        this.applicationRepository = applicationRepository;
        this.documentRepository = documentRepository;
    }

    // Applications
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }

    // Documents
    public Document saveDocuments(Long appId, Document document) {
        Application app = applicationRepository.findById(appId).orElseThrow();
        document.setApplication(app);
        return documentRepository.save(document);
    }

    public Document getDocumentsByApplicationId(Long appId) {
        return documentRepository.findByApplication_Id(appId);
    }
}

