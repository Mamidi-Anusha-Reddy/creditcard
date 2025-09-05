package com.scb.creditcardorigination.applicationFormFeature.model;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;
    private String email;
    private String creditCardType; // Gold, Silver, Platinum
    private String profileType;    // New, Existing

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Document documents;

    // Default constructor
    public Application() {}

    // Constructor
    public Application(String fullName, String phoneNumber, String email, String creditCardType, String profileType) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creditCardType = creditCardType;
        this.profileType = profileType;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCreditCardType() { return creditCardType; }
    public void setCreditCardType(String creditCardType) { this.creditCardType = creditCardType; }

    public String getProfileType() { return profileType; }
    public void setProfileType(String profileType) { this.profileType = profileType; }

    public Document getDocuments() { return documents; }
    public void setDocuments(Document documents) {
        this.documents = documents;
        if (documents != null) documents.setApplication(this);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", creditCardType='" + creditCardType + '\'' +
                ", profileType='" + profileType + '\'' +
                '}';
    }
}
