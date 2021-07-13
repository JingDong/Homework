package com.Bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // create bank account for Julia Roberts
        BankTransaction transaction = new BankTransaction();
        transaction.setAccountNumber("12345");
        transaction.setCustomerName("Julia Roberts");
        transaction.setEmailAddress("juliaRoberts@gmail.com");
        transaction.setPhoneNumber("333-444-5556");
        transaction.setBalance(1000);

        Scanner sc = new Scanner(System.in);
        float amount;
        while(true){
            System.out.println("Please select from the menu:");
            System.out.println("1. Show Account Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            int option = sc.nextInt();
            if(option == 1){
                System.out.println("Balance: " + transaction.getBalance());
            }
            else if(option == 2){
                System.out.println("Please input the amount for deposit:");
                amount = sc.nextFloat();
                transaction.deposit(amount);
            }
            else if(option == 3){
                System.out.println("Please input the amount for withdraw:");
                amount = sc.nextFloat();
                transaction.withdraw(amount);
            }

            else if(option == 4){
                break;
            }
            else{
                System.out.println("Please select 1-4 from Menu");
            }
        }

    }
}
