package com.scb.creditcardapplication.model;

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

    // one-to-one relation with Document
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Document documents;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCreditCardType() { return creditCardType; }
    public void setCreditCardType(String creditCardType) { this.creditCardType = creditCardType; }

    public String getProfileType() { return profileType; }
    public void setProfileType(String profileType) { this.profileType = profileType; }

    public Document getDocuments() { return documents; }
    public void setDocuments(Document documents) {
        this.documents = documents;
        if (documents != null) {
            documents.setApplication(this); // link both sides
        }
    }
}
