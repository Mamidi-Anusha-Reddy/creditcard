package com.scb.creditcardorigination.applicationFormFeature.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idProofPath;
    private String addressProofPath;
    private String incomeProofPath;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    // Default constructor
    public Document() {}

    // Constructor
    public Document(String idProofPath, String addressProofPath, String incomeProofPath) {
        this.idProofPath = idProofPath;
        this.addressProofPath = addressProofPath;
        this.incomeProofPath = incomeProofPath;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdProofPath() { return idProofPath; }
    public void setIdProofPath(String idProofPath) { this.idProofPath = idProofPath; }

    public String getAddressProofPath() { return addressProofPath; }
    public void setAddressProofPath(String addressProofPath) { this.addressProofPath = addressProofPath; }

    public String getIncomeProofPath() { return incomeProofPath; }
    public void setIncomeProofPath(String incomeProofPath) { this.incomeProofPath = incomeProofPath; }

    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", idProofPath='" + idProofPath + '\'' +
                ", addressProofPath='" + addressProofPath + '\'' +
                ", incomeProofPath='" + incomeProofPath + '\'' +
                '}';
    }
}
