package com.erdiagrams.atm;

public class Account {
    private String accountNo;
    private String holderName;
    private String currency;
    private double balance;
    private String accountType; // e.g., "Savings", "Current"
    private String cardNo; // Foreign Key pointing to LOGIN entity
    private String createdDate;

    public Account(String accountNo, String holderName, String currency, double balance, String accountType, String cardNo, String createdDate) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.currency = currency;
        this.balance = balance;
        this.accountType = accountType;
        this.cardNo = cardNo;
        this.createdDate = createdDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return String.format("Account No: %s | Holder: %s | Type: %s | Balance: %s %.2f | Card No: %s | Created: %s",
                accountNo, holderName, accountType, currency, balance, cardNo, createdDate);
    }
}
