package com.java.project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Login login = new Login();

        if (!login.login()) {
            return;
        }

        Account account = new Account(10000);
        MiniStatement miniStatement = new MiniStatement();
        PinChange pinChange = new PinChange();

        int choice;

        do {

            System.out.println("\n========== ATM SIMULATION ==========");
            System.out.println("1. Balance Enquiry");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    account.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter Deposit Amount : ");
                    double deposit = sc.nextDouble();
                    account.deposit(deposit);
                    break;

                case 3:
                    System.out.print("Enter Withdraw Amount : ");
                    double withdraw = sc.nextDouble();
                    account.withdraw(withdraw);
                    break;

                case 4:
                    miniStatement.display(account.getTransactions(), account.getBalance());
                    break;

                case 5:
                    pinChange.changePin(login);
                    break;

                case 6:
                    System.out.println("Thank You for Using Our ATM.");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}
