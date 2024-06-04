package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Transaction;

public class TransactionDAO {
    
    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_TRANSACTION = "INSERT INTO transaction (type," + "amount," + "transactionDate," +
                                                                            "originAccountID," + "destinationAccountID) " +
                                                                            "VALUES" + "(?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM transaction";
    private static final String SELECT_TRANSACTIONS_ORIGIN_ACCOUNT = "SELECT * FROM transaction WHERE originAccountID=?";
    private static final String SELECT_TRANSACTIONS_DESTINATION_ACCOUNT = "SELECT * FROM transaction WHERE destinationAccountID=?";

    public void insertTransaction(Transaction transaction) {
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {
            preparedStatement.setInt(1, transaction.getType());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setDate(3, transaction.getTransactionDate());
            preparedStatement.setInt(4, transaction.getOriginAccountID());
            preparedStatement.setInt(5, transaction.getDestinationAccountID());
            
            preparedStatement.executeUpdate();
            System.out.println("Transacción registrada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al registrar transacción: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public List<Transaction> selectAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSACTIONS)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Transaction transaction = new Transaction();
                    transaction.setTransactionID(resultSet.getInt("transactionID"));
                    transaction.setType(resultSet.getInt("type"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setTransactionDate(resultSet.getDate("transactionDate"));
                    transaction.setOriginAccountID(resultSet.getInt("originAccountID"));
                    transaction.setDestinationAccountID(resultSet.getInt("destinationAccountID"));
                    transactions.add(transaction);
                }
       } catch(SQLException e){
            System.out.println("Error al leer la base de datos: " + e.getMessage());
       } finally {
            DBConnection.closeConnection(connection);
       }

       return transactions;
    }

    public List<Transaction> selectAllTransactions(int account){
        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = DBConnection.getConnection();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTIONS_ORIGIN_ACCOUNT)){
                preparedStatement.setInt(1, account);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Transaction transaction = new Transaction();
                    transaction.setTransactionID(resultSet.getInt("transactionID"));
                    transaction.setType(resultSet.getInt("type"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setTransactionDate(resultSet.getDate("transactionDate"));
                    transaction.setOriginAccountID(resultSet.getInt("originAccountID"));
                    transaction.setDestinationAccountID(resultSet.getInt("destinationAccountID"));
                    transactions.add(transaction);
                }
       } catch(SQLException e){
            System.out.println("Error al leer la base de datos: " + e.getMessage());
       } finally {
            DBConnection.closeConnection(connection);
       }

       return transactions;
    }

    public List<Transaction> selectAllReceivedTransactions(int account){
        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = DBConnection.getConnection();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTIONS_DESTINATION_ACCOUNT)){
                preparedStatement.setInt(1, account);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Transaction transaction = new Transaction();
                    transaction.setTransactionID(resultSet.getInt("transactionID"));
                    transaction.setType(resultSet.getInt("type"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setTransactionDate(resultSet.getDate("transactionDate"));
                    transaction.setOriginAccountID(resultSet.getInt("originAccountID"));
                    transaction.setDestinationAccountID(resultSet.getInt("destinationAccountID"));
                    transactions.add(transaction);
                }
       } catch(SQLException e){
            System.out.println("Error al leer la base de datos: " + e.getMessage());
       } finally {
            DBConnection.closeConnection(connection);
       }

       return transactions;
    }
}
