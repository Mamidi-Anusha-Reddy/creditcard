package creditcard.repository;


import com.example.creditcard.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findByApplicationId(Long applicationId);
}

