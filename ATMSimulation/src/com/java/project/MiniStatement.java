package com.java.project;

import java.util.ArrayList;

public class MiniStatement {

    public void display(ArrayList<Transaction> transactions, double balance) {

        System.out.println("\n------ MINI STATEMENT ------");

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Found");
        } else {

            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }

        System.out.println("----------------------------");
        System.out.println("Available Balance : ₹" + balance);
    }
}
