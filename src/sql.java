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


# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/creditcard_db
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=50MB
app.upload.dir=uploads

# Server Configuration
server.port=8080

# CORS Configuration
spring.web.cors.allowed-origins=http://localhost:3000
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true



    -- Create database for credit card application
CREATE DATABASE creditcard_db;

-- Connect to the database
\c creditcard_db;

-- Create applications table
CREATE TABLE IF NOT EXISTS applications (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    credit_card_type VARCHAR(50) NOT NULL,
    profile_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create documents table
CREATE TABLE IF NOT EXISTS documents (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT REFERENCES applications(id) ON DELETE CASCADE,
    id_proof_path VARCHAR(500),
    address_proof_path VARCHAR(500),
    income_proof_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_applications_email ON applications(email);
CREATE INDEX idx_applications_credit_card_type ON applications(credit_card_type);
CREATE INDEX idx_applications_profile_type ON applications(profile_type);
CREATE INDEX idx_documents_application_id ON documents(application_id);

-- Insert sample data (optional)
-- INSERT INTO applications (full_name, phone_number, email, credit_card_type, profile_type) 
-- VALUES ('John Doe', '1234567890', 'john.doe@example.com', 'Gold Card', 'New Profile');

-- Verify tables created
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';

COMMENT ON TABLE applications IS 'Store credit card application details';
COMMENT ON TABLE documents IS 'Store file paths for uploaded documents';
