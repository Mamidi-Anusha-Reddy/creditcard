package com.scb.creditcardorigination.applicationFormFeature.repository;

import com.scb.creditcardorigination.applicationFormFeature.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Custom query methods can be added here if needed
}
