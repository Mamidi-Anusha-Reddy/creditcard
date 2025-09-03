package com.scb.creditcardorigination.model;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;
    private String creditCardType;
    private String profileType;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Document documents;

    // getters and setters
}
