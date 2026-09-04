package com.java.project;

import java.util.ArrayList;

public class Account {

    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(double balance) {
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    public void checkBalance() {
        System.out.println("Current Balance : ₹" + balance);
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Amount");
            return;
        }

        balance += amount;
        transactions.add(new Transaction("Deposit", amount));

        System.out.println("Deposit Successful");
    }

    public void withdraw(double amount) {

        if (amount > balance) {
            System.out.println("Insufficient Balance");
            return;
        }

        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount));

        System.out.println("Withdrawal Successful");
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
}
