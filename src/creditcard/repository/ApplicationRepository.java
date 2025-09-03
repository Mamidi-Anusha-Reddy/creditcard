package com.scb.creditcardapplication.repository;

import com.scb.creditcardapplication.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {}
