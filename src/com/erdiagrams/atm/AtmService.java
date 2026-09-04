package com.erdiagrams.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmService {

    private Map<String, Login> loginDatabase = new HashMap<>();
    private Map<String, Account> accountDatabase = new HashMap<>(); // mapped by Card No
    private Map<String, List<Transaction>> transactionHistory = new HashMap<>(); // mapped by Account No
    private int transactionCounter = 1001;

    public AtmService() {
        seedSampleData();
    }

    private void seedSampleData() {
        // Pre-populate realistic sample data based on ER Diagram
        Login userLogin = new Login("1234567890123456", "1234", "DEBIT", "12/28", "ACTIVE", "Global National Bank", 0);
        Account userAccount = new Account("ACC-987654321", "John Doe", "USD", 5000.00, "Savings", "1234567890123456", "2024-01-10");

        loginDatabase.put(userLogin.getCardNo(), userLogin);
        accountDatabase.put(userLogin.getCardNo(), userAccount);
        transactionHistory.put(userAccount.getAccountNo(), new ArrayList<>());
    }

    public Login getLoginDetails(String cardNo) {
        return loginDatabase.get(cardNo);
    }

    public Account getAccountByCardNo(String cardNo) {
        return accountDatabase.get(cardNo);
    }

    public Login authenticate(String cardNo, String inputPin) {
        Login login = loginDatabase.get(cardNo);
        if (login == null) {
            System.out.println("[!] Invalid Card Number. Card not found.");
            return null;
        }

        if ("BLOCKED".equalsIgnoreCase(login.getCardStatus())) {
            System.out.println("[!] Your card is BLOCKED due to multiple incorrect PIN attempts. Please contact customer support.");
            return null;
        }

        if (login.getPin().equals(inputPin)) {
            login.resetFailedAttempts();
            System.out.println("\n[+] Authentication Successful! Welcome to " + login.getBankName() + ".");
            return login;
        } else {
            login.incrementFailedAttempts();
            int remaining = 3 - login.getFailedAttempts();
            if (login.getFailedAttempts() >= 3) {
                System.out.println("[!] Incorrect PIN. Your card has been BLOCKED due to 3 failed attempts.");
            } else {
                System.out.println("[!] Incorrect PIN. Remaining attempts: " + remaining);
            }
            return null;
        }
    }

    public void checkBalance(Account account) {
        System.out.println("\n=============================================");
        System.out.println("            ACCOUNT BALANCE ENQUIRY          ");
        System.out.println("=============================================");
        System.out.println("Account No   : " + account.getAccountNo());
        System.out.println("Holder Name  : " + account.getHolderName());
        System.out.println("Account Type : " + account.getAccountType());
        System.out.printf("Current Balance : %s %.2f\n", account.getCurrency(), account.getBalance());
        System.out.println("=============================================");

        recordTransaction("BALANCE_ENQUIRY", 0.0, "SUCCESS", account.getBalance(), account.getAccountNo());
    }

    public boolean deposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("[!] Invalid deposit amount. Amount must be greater than zero.");
            recordTransaction("DEPOSIT", amount, "FAILED", account.getBalance(), account.getAccountNo());
            return false;
        }

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        System.out.printf("[+] Successfully deposited %s %.2f. New Balance: %s %.2f\n",
                account.getCurrency(), amount, account.getCurrency(), newBalance);

        recordTransaction("DEPOSIT", amount, "SUCCESS", newBalance, account.getAccountNo());
        return true;
    }

    public boolean withdraw(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("[!] Invalid withdrawal amount.");
            recordTransaction("WITHDRAWAL", amount, "FAILED", account.getBalance(), account.getAccountNo());
            return false;
        }

        if (amount > account.getBalance()) {
            System.out.printf("[!] Insufficient Funds. Your balance is %s %.2f\n", account.getCurrency(), account.getBalance());
            recordTransaction("WITHDRAWAL", amount, "FAILED", account.getBalance(), account.getAccountNo());
            return false;
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        System.out.printf("[+] Successfully withdrew %s %.2f. Remaining Balance: %s %.2f\n",
                account.getCurrency(), amount, account.getCurrency(), newBalance);

        recordTransaction("WITHDRAWAL", amount, "SUCCESS", newBalance, account.getAccountNo());
        return true;
    }

    public boolean changePin(Login login, Account account, String oldPin, String newPin) {
        if (!login.getPin().equals(oldPin)) {
            System.out.println("[!] Current PIN verification failed.");
            recordTransaction("PIN_CHANGE", 0.0, "FAILED", account.getBalance(), account.getAccountNo());
            return false;
        }

        if (newPin == null || newPin.length() != 4 || !newPin.matches("\\d+")) {
            System.out.println("[!] New PIN must be exactly 4 numeric digits.");
            recordTransaction("PIN_CHANGE", 0.0, "FAILED", account.getBalance(), account.getAccountNo());
            return false;
        }

        login.setPin(newPin);
        System.out.println("[+] PIN changed successfully!");
        recordTransaction("PIN_CHANGE", 0.0, "SUCCESS", account.getBalance(), account.getAccountNo());
        return true;
    }

    public void printMiniStatement(Account account) {
        List<Transaction> txns = transactionHistory.getOrDefault(account.getAccountNo(), new ArrayList<>());

        System.out.println("\n==========================================================================");
        System.out.println("                           MINI STATEMENT                                 ");
        System.out.println("==========================================================================");
        System.out.println("Account No  : " + account.getAccountNo() + " | Holder: " + account.getHolderName());
        System.out.printf("Cur. Balance: %s %.2f\n", account.getCurrency(), account.getBalance());
        System.out.println("--------------------------------------------------------------------------");

        if (txns.isEmpty()) {
            System.out.println("No recent transactions found.");
        } else {
            System.out.printf("%-10s | %-16s | %-10s | %-9s | %-12s | %-19s\n",
                    "TXN ID", "TYPE", "AMOUNT", "STATUS", "BAL AFTER", "DATE & TIME");
            System.out.println("--------------------------------------------------------------------------");
            for (Transaction t : txns) {
                System.out.printf("%-10s | %-16s | %-10.2f | %-9s | %-12.2f | %-19s\n",
                        t.getTransactionId(), t.getType(), t.getAmount(), t.getStatus(), t.getBalanceAfter(), t.getDateTime());
            }
        }
        System.out.println("==========================================================================\n");
    }

    private void recordTransaction(String type, double amount, String status, double balanceAfter, String accountNo) {
        String txnId = "TXN" + (transactionCounter++);
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Transaction txn = new Transaction(txnId, type, amount, dateTime, status, balanceAfter, accountNo);
        transactionHistory.computeIfAbsent(accountNo, k -> new ArrayList<>()).add(txn);
    }
}
