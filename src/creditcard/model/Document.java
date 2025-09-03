package creditcard.model;


import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_proof_path")
    private String idProofPath;

    @Column(name = "address_proof_path")
    private String addressProofPath;

    @Column(name = "income_proof_path")
    private String incomeProofPath;

    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    // Getters and Setters
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
}

