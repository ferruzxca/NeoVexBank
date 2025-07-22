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

INSERT INTO user (email, password, role) VALUES
('ferruzca@neovex.com',    '$2a$10$Imr9CqLFuxKpMoFXImrtLOxJNOJcQZZ.YzrbR5KRxQzCH4jiuc1Fq', 'ADMIN'),
('jade@neovex.com', '$2a$10$Imr9CqLFuxKpMoFXImrtLOxJNOJcQZZ.YzrbR5KRxQzCH4jiuc1Fq', 'USER'),
('cliente2@neovex.com', '$2a$10$Imr9CqLFuxKpMoFXImrtLOxJNOJcQZZ.YzrbR5KRxQzCH4jiuc1Fq', 'USER'),
('cliente3@neovex.com', '$2a$10$Imr9CqLFuxKpMoFXImrtLOxJNOJcQZZ.YzrbR5KRxQzCH4jiuc1Fq', 'USER');

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

INSERT INTO customer (name, address, phone, email, updated_at, user_id) VALUES
('Raúl Ferruzca', 'Av. Siempre Viva 100', '5512345678', 'cliente1@correo.com', NOW(), 2),
('Marta Martínez', 'Calle Sol 555', '5523456789', 'cliente2@correo.com', NOW(), 3),
('Juan Pérez', 'Blvd. Luna 234', '5534567890', 'cliente3@correo.com', NOW(), 4),
('Ana López', 'Prol. Reforma 999', '5545678901', 'cliente4@correo.com', NOW(), 1);

-- Tabla: account_type
CREATE TABLE account_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO account_type (type_name, description) VALUES
('Cuenta Corriente', 'Cuenta de uso diario'),
('Cuenta de Ahorros', 'Genera intereses mensuales'),
('Cuenta Empresarial', 'Para empresas y negocios'),
('Cuenta Digital', 'Manejo 100% en línea');

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

INSERT INTO account (customer_id, account_number, balance, account_type_id, status) VALUES
(8, '0011001122', 15000.00, 1, 'ACTIVA'),
(6, '0022002211', 8000.00, 2, 'ACTIVA'),
(7, '0033003311', 12000.00, 3, 'ACTIVA'),
(8, '0044004411', 500.00, 2, 'INACTIVA');

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

INSERT INTO transaction (account_id, type, amount, date, description) VALUES
(5, 'DEPÓSITO', 5000, NOW(), 'Depósito inicial'),
(5, 'RETIRO', -500, NOW(), 'Compra en supermercado'),
(6, 'DEPÓSITO', 8000, NOW(), 'Transferencia nómina'),
(7, 'DEPÓSITO', 12000, NOW(), 'Pago factura');

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

INSERT INTO transfer (sender_account_id, receiver_account_id, amount, transfer_date) VALUES
(1, 2, 500, NOW()),
(2, 3, 200, NOW()),
(3, 1, 1000, NOW()),
(1, 3, 300, NOW());

-- Tabla: login_session
CREATE TABLE login_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    login_time DATETIME,
    logout_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO login_session (user_id, login_time, logout_time) VALUES
(2, NOW(), NULL),
(3, NOW(), NULL),
(4, NOW(), NULL),
(1, NOW(), NULL);

-- Tabla: password_change_log
CREATE TABLE password_change_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    change_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO password_change_log (user_id, change_date) VALUES
(2, NOW()),
(3, NOW()),
(4, NOW()),
(1, NOW());

-- Tabla: statement
CREATE TABLE statement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    generated_at DATETIME NOT NULL,
    pdf_path VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

INSERT INTO statement (account_id, generated_at, pdf_path) VALUES
(1, NOW(), '/statements/statement_1_julio.pdf'),
(2, NOW(), '/statements/statement_2_julio.pdf'),
(3, NOW(), '/statements/statement_3_julio.pdf'),
(4, NOW(), '/statements/statement_4_julio.pdf');

-- Tabla: audit_log
CREATE TABLE audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    action VARCHAR(255),
    timestamp DATETIME,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO audit_log (user_id, action, timestamp) VALUES
(2, 'Inicio de sesión', NOW()),
(3, 'Transferencia realizada', NOW()),
(4, 'Cambio de contraseña', NOW()),
(1, 'Consulta de saldo', NOW());

-- Tabla: bank_branch
CREATE TABLE bank_branch (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255)
);

INSERT INTO bank_branch (name, location) VALUES
('Sucursal Centro', 'Av. Reforma 100, CDMX'),
('Sucursal Norte', 'Calz. Vallejo 800, CDMX'),
('Sucursal Sur', 'Insurgentes Sur 3000, CDMX'),
('Sucursal Satélite', 'Periférico 5000, Naucalpan');