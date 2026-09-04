package com.erdiagrams.atm;

public class Transaction {
    private String transactionId;
    private String type; // e.g., "DEPOSIT", "WITHDRAWAL", "BALANCE_ENQUIRY", "PIN_CHANGE"
    private double amount;
    private String dateTime;
    private String status; // "SUCCESS", "FAILED"
    private double balanceAfter;
    private String accountNo; // Foreign Key pointing to ACCOUNT entity

    public Transaction(String transactionId, String type, double amount, String dateTime, String status, double balanceAfter, String accountNo) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
        this.balanceAfter = balanceAfter;
        this.accountNo = accountNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return String.format("Txn ID: %-10s | Type: %-15s | Amount: %-8.2f | Status: %-8s | Balance After: %-8.2f | Date: %s",
                transactionId, type, amount, status, balanceAfter, dateTime);
    }
}
