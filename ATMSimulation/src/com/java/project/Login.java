package com.java.project;

import java.util.Scanner;

public class Login {

    private int pin = 1234;

    public boolean login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ATM PIN : ");
        int enteredPin = sc.nextInt();

        if (enteredPin == pin) {
            System.out.println("Login Successful");
            return true;
        } else {
            System.out.println("Invalid PIN");
            return false;
        }
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
