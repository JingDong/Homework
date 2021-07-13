package com.Bank;

public class BankTransaction {
    private String accountNumber;
    private float balance;
    private String customerName;
    private String emailAddress;
    private String phoneNumber;


    BankTransaction(){}
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
    public void customerInfo(){
        System.out.println("Customer Name: " + this.customerName);
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Balance: " + this.balance);
        System.out.println("Email: " + this.emailAddress);
        System.out.println("Phone Number:" + this.phoneNumber);

    }

}
