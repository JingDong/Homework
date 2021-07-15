package com.Bank;

public interface CustomerDAOInterface {
    public void addCustomer(String customerName, String phoneNumber, String emailAddress);
    public void updateCustomer(int accountNumber, String newName);
    public void updateEmail(int accountNumber, String newEmail);
    public void updatePhone(int accountNumber, String newPhone);
    public void deleteCustomer(int accountNumber);
    public void displayAllCustomer();
    public Customer displayCustomerInfo(String customerName);
    public void displayBalance(int accountNumber);
    public void deposit(int accountNumber, float amount);
    public void withdraw(int accountNumber, float amount);

}
