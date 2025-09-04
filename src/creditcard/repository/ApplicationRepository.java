package com.scb.creditcardorigination.applicationFormFeature.repository;

import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
