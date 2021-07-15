package com.Bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // create bank customer database
        CustomerDAO customerDAO = new CustomerDAO();
        Scanner sc = new Scanner(System.in);
        String customerName;
        String emailAddress;
        String phoneNumber;
        String flag = "Y";
        System.out.println("Please create the customer list");
        while(flag.equalsIgnoreCase("Y")){
            System.out.println("Please input customer name:");
            customerName = sc.nextLine();
            System.out.println("Please input customer phone number:");
            phoneNumber = sc.nextLine();
            System.out.println("Please input customer email address:");
            emailAddress = sc.nextLine();
            customerDAO.addCustomer(customerName, phoneNumber, emailAddress);
            System.out.println("Would you like to add more customer?(Y/N)");
            flag = sc.nextLine();
        }
        System.out.println("Customer database is created");
        customerDAO.displayAllCustomer();



        float amount;
        int accountNumber;
        Customer currentCustomer;
        System.out.println("Please input the customer's name");
        customerName = sc.nextLine();
        currentCustomer = customerDAO.displayCustomerInfo(customerName);
        if(currentCustomer == null){
            System.out.println("No such customer in the database");
            return;
        }

        int currentAccountNumber = currentCustomer.getAccountNumber();



        while(currentCustomer != null){
            System.out.println("********************************************");
            System.out.println(currentCustomer.getCustomerName() + " please select from the following (1 - 7)");
            System.out.println("1. Show Account Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Update customer's name");
            System.out.println("5. Update customer's email");
            System.out.println("6. Update customer's phone number");
            System.out.println("7. Exit");

            String option = sc.nextLine();
            if(option.equals("1")){
                customerDAO.displayBalance(currentAccountNumber);
            }
            else if(option.equals("2")){
                System.out.println("Please input the amount for deposit:");
                amount = sc.nextFloat();
                customerDAO.deposit(currentAccountNumber,amount);
            }
            else if(option.equals("3")){
                System.out.println("Please input the amount for withdraw:");
                amount = sc.nextFloat();
                customerDAO.withdraw(currentAccountNumber, amount);
            }
            else if(option.equals("4")){
                System.out.println("Please input the new Name:");
                String newName = sc.nextLine();
                customerDAO.updateCustomer(currentAccountNumber, newName);
            }
            else if(option.equals("5")){
                System.out.println("Please input the new Email:");
                String newEmail = sc.nextLine();
                customerDAO.updateEmail(currentAccountNumber, newEmail);
            }

            else if(option.equals("6")){
                System.out.println("Please input the new phone number:");
                String newPhone = sc.nextLine();
                customerDAO.updatePhone(currentAccountNumber, newPhone);
            }

            else if(option.equals("7")){
                break;
            }
            else{
                System.out.println("Please select 1-7 from Menu");
            }
        }

        customerDAO.displayAllCustomer();
        customerDAO.deleteCustomer(currentAccountNumber);
        customerDAO.displayAllCustomer();

    }
}
