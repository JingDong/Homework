package com.Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerMySQL implements CustomerDAOInterface {
    static final String DB_URL = "jdbc:mysql://database-1.ctu4j6haym38.us-east-1.rds.amazonaws.com:3306/BankDB";
    static final String USER = "admin";
    static final String PASS = "nGLm7X9rdCTVh594ai9z";
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (accountNumber, customerName, emailAddress, phoneNumber,balance) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_NAME = "select accountNumber,customerName,emailAddress,phoneNumber, balance from users where customerName =?;";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where accountNumber = ?;";
    private static final String UPDATE_USERS_SQL = "update users set customerName = ?, where accountNumber = ?;";
    private static final String UPDATE_EMAIL_SQL = "update users set emailAddress= ?, where accountNumber = ?;";
    private static final String UPDATE_PHONE_SQL = "update users set phoneNumber =? where accountNumber = ?;";
    private static final String SELECT_BALANCE_SQL = "select balance from users where accountNumber = ?;";
    private static final String UPDATE_BALANCE_SQL = "update user set balance =? where accountNumber = ?;";


    public CustomerMySQL(){}

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void addCustomer(Customer customer){
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, customer.getAccountNumber());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmailAddress());
            preparedStatement.setFloat(5, customer.getBalance());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    @Override
    public void updateCustomer(int accountNumber, String newName) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, newName);
            statement.setInt(2, accountNumber);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateEmail(int accountNumber, String newEmail) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL_SQL);) {
            statement.setString(1, newEmail);
            statement.setInt(2, accountNumber);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updatePhone(int accountNumber, String newPhone) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PHONE_SQL);) {
            statement.setString(1, newPhone);
            statement.setInt(2, accountNumber);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteCustomer(int accountNumber) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, accountNumber);
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void displayAllCustomer() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("accountNumber");
                String name = rs.getString("customerName");
                String email = rs.getString("emailAddress");
                String phone = rs.getString("phoneNumber");
                Float balance = rs.getFloat("balance");
                customer = new Customer(name, email, phone, balance, id);
                System.out.println(id +"  " + name +"  " + email +"  " + phone +"  " +balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Customer displayCustomerInfo(String customerName) {
        // using try-with-resources to avoid closing resources (boiler plate code)
       Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);) {

            // Step 3: Execute the query or update query
            preparedStatement.setString(1, customerName);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("accountNumber");
                String name = rs.getString("customerName");
                String email = rs.getString("emailAddress");
                String phone = rs.getString("phoneNumber");
                Float balance = rs.getFloat("balance");
                customer = new Customer(name, email, phone, balance, id);
                System.out.println(id +"  " + name +"  " + email +"  " + phone +"  " +balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public Float displayBalance(int accountNumber) {
        Float balance = Float.valueOf(0);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BALANCE_SQL);) {
            statement.setInt(1, accountNumber);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                balance = rs.getFloat("balance");
                System.out.println("balance : " + balance);
            }


        }catch (SQLException e) {
            printSQLException(e);
        }
        return balance;
    }

    @Override
    public void deposit(int accountNumber, float amount) {
        Float balance = displayBalance(accountNumber) + amount;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE_SQL);) {
            statement.setFloat(1, balance);
            statement.setInt(2, accountNumber);
            System.out.println("balance : " + balance);

        }catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void withdraw(int accountNumber, float amount) {
        Float balance = displayBalance(accountNumber) - amount;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE_SQL);) {
            statement.setFloat(1, balance);
            statement.setInt(2, accountNumber);
            System.out.println("balance : " + balance);

        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
