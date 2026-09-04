-- Create Database
CREATE DATABASE atm_db;
USE atm_db;

-- 1. Table for Login / Card Details
CREATE TABLE login (
    card_no VARCHAR(20) PRIMARY KEY,
    pin VARCHAR(10),
    card_type VARCHAR(20),
    expiry_date VARCHAR(10),
    card_status VARCHAR(20),
    bank_name VARCHAR(50),
    failed_attempts INT
);

-- 2. Table for Account Details
CREATE TABLE account (
    account_no VARCHAR(20) PRIMARY KEY,
    holder_name VARCHAR(50),
    currency VARCHAR(10),
    balance DOUBLE,
    account_type VARCHAR(20),
    card_no VARCHAR(20),
    created_date VARCHAR(20),
    FOREIGN KEY (card_no) REFERENCES login(card_no)
);

-- 3. Table for Transactions
CREATE TABLE transaction (
    transaction_id VARCHAR(20) PRIMARY KEY,
    type VARCHAR(30),
    amount DOUBLE,
    date_time VARCHAR(30),
    status VARCHAR(20),
    balance_after DOUBLE,
    account_no VARCHAR(20),
    FOREIGN KEY (account_no) REFERENCES account(account_no)
);


-- =======================================================
-- Sample Data Insertion
-- =======================================================

-- Inserting data into login
INSERT INTO login VALUES ('1234567890123456', '1234', 'DEBIT', '12/28', 'ACTIVE', 'Global National Bank', 0);
INSERT INTO login VALUES ('9876543210987654', '4321', 'CREDIT', '06/29', 'ACTIVE', 'State Bank', 0);

-- Inserting data into account
INSERT INTO account VALUES ('ACC-987654321', 'John Doe', 'USD', 5000.0, 'Savings', '1234567890123456', '2024-01-10');
INSERT INTO account VALUES ('ACC-123456789', 'Jane Smith', 'USD', 10000.0, 'Current', '9876543210987654', '2024-02-15');

-- Inserting data into transaction
INSERT INTO transaction VALUES ('TXN1001', 'DEPOSIT', 5000.0, '2024-01-10 10:00:00', 'SUCCESS', 5000.0, 'ACC-987654321');
INSERT INTO transaction VALUES ('TXN1002', 'BALANCE_ENQUIRY', 0.0, '2024-01-11 11:30:00', 'SUCCESS', 5000.0, 'ACC-987654321');
INSERT INTO transaction VALUES ('TXN1003', 'WITHDRAWAL', 500.0, '2024-01-12 14:15:00', 'SUCCESS', 4500.0, 'ACC-987654321');
INSERT INTO transaction VALUES ('TXN1004', 'DEPOSIT', 1000.0, '2024-01-15 16:00:00', 'SUCCESS', 5500.0, 'ACC-987654321');


-- =======================================================
-- Simple Select Queries
-- =======================================================

-- View all cards/logins
SELECT * FROM login;

-- View all accounts
SELECT * FROM account;

-- View all transactions
SELECT * FROM transaction;
