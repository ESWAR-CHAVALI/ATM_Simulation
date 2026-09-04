package com.java.project;

import java.util.Scanner;

public class PinChange {

    public void changePin(Login login) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Current PIN : ");
        int oldPin = sc.nextInt();

        if (oldPin != login.getPin()) {
            System.out.println("Incorrect PIN");
            return;
        }

        System.out.print("Enter New PIN : ");
        int newPin = sc.nextInt();

        System.out.print("Confirm New PIN : ");
        int confirmPin = sc.nextInt();

        if (newPin == confirmPin) {
            login.setPin(newPin);
            System.out.println("PIN Changed Successfully.");
        } else {
            System.out.println("PIN Mismatch.");
        }
    }
}
