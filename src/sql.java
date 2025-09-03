public class sql {


    CREATE TABLE applications (
            id SERIAL PRIMARY KEY,
            full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    credit_card_type VARCHAR(50),
    profile_type VARCHAR(50)
);

    CREATE TABLE documents (
            id SERIAL PRIMARY KEY,
            id_proof_path VARCHAR(500),
    address_proof_path VARCHAR(500),
    income_proof_path VARCHAR(500),
    application_id INT UNIQUE,
    CONSTRAINT fk_application FOREIGN KEY (application_id)
    REFERENCES applications(id) ON DELETE CASCADE
);

{
  "fullName": "Alice",
  "phoneNumber": "9876543210",
  "creditCardType": "Gold",
  "profileType": "New",
  "documents": {
    "idProofPath": "/uploads/id/alice_id.pdf",
    "addressProofPath": "/uploads/address/alice_address.pdf",
    "incomeProofPath": "/uploads/income/alice_income.pdf"
  }
}


}
