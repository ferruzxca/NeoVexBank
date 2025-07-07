-- Crear base de datos
CREATE DATABASE IF NOT EXISTS neovexbank;
USE neovexbank;

-- Tabla: user
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Tabla: customer
CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255),
    updated_at DATETIME,
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: account_type
CREATE TABLE account_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

-- Tabla: account
CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    balance DOUBLE DEFAULT 0.0,
    account_type_id BIGINT,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (account_type_id) REFERENCES account_type(id)
);

-- Tabla: transaction
CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    type VARCHAR(50),
    amount DOUBLE NOT NULL,
    date DATETIME NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Tabla: transfer
CREATE TABLE transfer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_account_id BIGINT NOT NULL,
    receiver_account_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    transfer_date DATETIME NOT NULL,
    FOREIGN KEY (sender_account_id) REFERENCES account(id),
    FOREIGN KEY (receiver_account_id) REFERENCES account(id)
);

-- Tabla: login_session
CREATE TABLE login_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    login_time DATETIME,
    logout_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: password_change_log
CREATE TABLE password_change_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    change_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: statement
CREATE TABLE statement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    generated_at DATETIME NOT NULL,
    pdf_path VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Tabla: audit_log
CREATE TABLE audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    action VARCHAR(255),
    timestamp DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: bank_branch
CREATE TABLE bank_branch (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255)
);