package com.erdiagrams.atm;

public class Login {
    private String cardNo;
    private String pin;
    private String cardType;
    private String expiryDate;
    private String cardStatus; // e.g., "ACTIVE", "BLOCKED"
    private String bankName;
    private int failedAttempts;

    public Login(String cardNo, String pin, String cardType, String expiryDate, String cardStatus, String bankName, int failedAttempts) {
        this.cardNo = cardNo;
        this.pin = pin;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.cardStatus = cardStatus;
        this.bankName = bankName;
        this.failedAttempts = failedAttempts;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
        if (this.failedAttempts >= 3) {
            this.cardStatus = "BLOCKED";
        }
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
    }

    @Override
    public String toString() {
        return String.format("Card No: %s | Type: %s | Bank: %s | Expiry: %s | Status: %s | Failed Attempts: %d",
                cardNo, cardType, bankName, expiryDate, cardStatus, failedAttempts);
    }
}
