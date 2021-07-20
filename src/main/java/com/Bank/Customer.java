package com.Bank;

public class Customer {
    private int accountNumber;
    private float balance;
    private String customerName;
    private String emailAddress;
    private String phoneNumber;


    Customer(String customerName, String phoneNumber, String emailAddress){
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.balance = 50;
        this.accountNumber = (int)(Math.random() *(200000 - 100000 + 1)) + 100000;
    }
    Customer(String customerName, String phoneNumber, String emailAddress, float balance, int accountNumber){
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
    public int getAccountNumber() {
        return accountNumber;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void deposit(float amount){
        this.balance += amount;
        System.out.println(this.customerName + "balance: " + this.balance);
    }
    public void withdraw(float amount){
        if(this.balance > amount){
            this.balance -= amount;
            System.out.println(this.customerName + "balance: " + this.balance);
        }
        else{
            System.out.println("Insufficient amount. Cannot withdraw." );
        }
    }

}
