package com.Bank;
import java.util.Map;
import java.util.HashMap;

public class CustomerDAO implements CustomerDAOInterface{
    Map<Integer, Customer> customerList = new HashMap<>();

    // Add new customer to map <accountNumber, customer>
    @Override
    public void addCustomer(Customer customer){
        customerList.put(customer.getAccountNumber(), customer);
    }

    //Update user's name
    @Override
    public void updateCustomer(int accountNumber, String newName){
        Customer current = customerList.get(accountNumber);
        current.setCustomerName(newName);
        customerList.put(accountNumber, current);
        System.out.println("Name is changed to " + newName);
    }

    //Update user's email
    @Override
    public void updateEmail(int accountNumber, String newEmail){
        customerList.get(accountNumber).setEmailAddress(newEmail);
        System.out.println("Email is changed to "+ newEmail);
    }

    //update user's phone number
    @Override
    public void updatePhone(int accountNumber, String newPhone){
        customerList.get(accountNumber).setPhoneNumber(newPhone);
        System.out.println("Phone number is changed to " + newPhone);
    }

    //delete a user by account number
    @Override
    public void deleteCustomer(int accountNumber){
        String oldName = customerList.get(accountNumber).getCustomerName();
        customerList.remove(accountNumber);
        System.out.println(oldName + " is deleted from database");
    }

    //display all users
    @Override
    public void displayAllCustomer(){
        System.out.println("Current customers in the database: ");
        for(Integer i: customerList.keySet()){
            System.out.println(customerList.get(i).getCustomerName());
        }
    }

    //display user's info by selecting the name
    @Override
    public Customer displayCustomerInfo(String customerName){

        for(Customer c: customerList.values()){
            if(c.getCustomerName().equalsIgnoreCase(customerName)){
                System.out.println("Customer Name: " + c.getCustomerName());
                System.out.println("Account Number: " + c.getAccountNumber());
                System.out.println("Email: " + c.getEmailAddress());
                System.out.println("Phone Number:" + c.getPhoneNumber());
                System.out.println();
                return c;
            }
        }
        return null;
    }

    //Display account balance
    @Override
    public Float displayBalance(int accountNumber){
        System.out.println("Balance: " + customerList.get(accountNumber).getBalance());
        return customerList.get(accountNumber).getBalance();

    }
    //Deposit Money - Don't allow user to deposit less than $5 and more than $10000
    @Override
    public void deposit(int accountNumber, float amount){
        if(amount < 5 || amount > 10000){
            System.out.println("Please deposit amount between $5 and $10000");
        }
        else{
            customerList.get(accountNumber).deposit(amount);
        }

    }

    // Withdraw money
    @Override
    public void withdraw(int accountNumber, float amount){
        customerList.get(accountNumber).withdraw(amount);
    }

}
