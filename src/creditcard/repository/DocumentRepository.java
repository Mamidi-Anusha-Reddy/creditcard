package com.scb.creditcardapplication.repository;

import com.scb.creditcardapplication.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {}
