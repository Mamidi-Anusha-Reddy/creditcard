package com.scb.creditcardorigination.applicationFormFeature.repository;

import com.scb.creditcardorigination.applicationFormFeature.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Custom query methods can be added here if needed
    List<Application> findByEmail(String email);
    List<Application> findByCreditCardType(String creditCardType);
    List<Application> findByProfileType(String profileType);
}
