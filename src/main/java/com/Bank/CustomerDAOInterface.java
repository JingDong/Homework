package com.Bank;

import java.sql.SQLException;

public interface CustomerDAOInterface {
    public void addCustomer(Customer customer);
    public void updateCustomer(int accountNumber, String newName);
    public void updateEmail(int accountNumber, String newEmail);
    public void updatePhone(int accountNumber, String newPhone);
    public void deleteCustomer(int accountNumber);
    public void displayAllCustomer();
    public Customer displayCustomerInfo(String customerName);
    public Float displayBalance(int accountNumber);
    public void deposit(int accountNumber, float amount);
    public void withdraw(int accountNumber, float amount);

}
