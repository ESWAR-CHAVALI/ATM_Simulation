package com.erdiagrams.atm;

import java.util.Scanner;

public class AtmSimulationMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AtmService atmService = new AtmService();

        System.out.println("=================================================");
        System.out.println("       WELCOME TO ATM SIMULATION SYSTEM          ");
        System.out.println("=================================================");
        System.out.println("[Demo Credentials]");
        System.out.println("Card Number : 1234567890123456");
        System.out.println("PIN         : 1234");
        System.out.println("=================================================\n");

        Login authenticatedUser = null;
        Account userAccount = null;

        // Login Loop
        while (authenticatedUser == null) {
            System.out.print("Enter 16-Digit Card Number (or type 'exit' to quit): ");
            String cardNo = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(cardNo)) {
                System.out.println("Thank you for using ATM Simulation System. Goodbye!");
                scanner.close();
                return;
            }

            System.out.print("Enter 4-Digit PIN: ");
            String pin = scanner.nextLine().trim();

            authenticatedUser = atmService.authenticate(cardNo, pin);
            if (authenticatedUser != null) {
                userAccount = atmService.getAccountByCardNo(cardNo);
            } else {
                System.out.println("Please try again.\n");
            }
        }

        // ATM Operations Menu Loop
        int choice = 0;
        do {
            System.out.println("\n=============================================");
            System.out.println("                 ATM MAIN MENU               ");
            System.out.println("=============================================");
            System.out.println("1. Balance Enquiry");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Account & Card Details");
            System.out.println("7. Exit / Logout");
            System.out.println("=============================================");
            System.out.print("Select an option (1-7): ");

            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        atmService.checkBalance(userAccount);
                        break;

                    case 2:
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                        atmService.deposit(userAccount, depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                        atmService.withdraw(userAccount, withdrawAmount);
                        break;

                    case 4:
                        atmService.printMiniStatement(userAccount);
                        break;

                    case 5:
                        System.out.print("Enter Current PIN: ");
                        String oldPin = scanner.nextLine().trim();
                        System.out.print("Enter New 4-Digit PIN: ");
                        String newPin = scanner.nextLine().trim();
                        atmService.changePin(authenticatedUser, userAccount, oldPin, newPin);
                        break;

                    case 6:
                        System.out.println("\n--- LOGIN & CARD ENTITY DETAILS ---");
                        System.out.println(authenticatedUser);
                        System.out.println("\n--- ACCOUNT ENTITY DETAILS ---");
                        System.out.println(userAccount);
                        break;

                    case 7:
                        System.out.println("\nThank you for using " + authenticatedUser.getBankName() + " ATM services. Please take your card.");
                        break;

                    default:
                        System.out.println("[!] Invalid Choice! Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Input Error: Please enter a valid numerical value.");
            }

        } while (choice != 7);

        scanner.close();
    }
}
