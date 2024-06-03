package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Account;

public class AccountDAO {

    private static final String INSERT_ACCOUNT = "INSERT INTO account (number," + "username," + "password," + 
                                                                    "type," + "balance," + "openDate," + "cancelDate," +
                                                                    "status," + "userID) " + "VALUES " +
                                                                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET username = ?," + "password = ?," +
                                                                    "type = ?," + "balance = ?," + "openDate = ?," +
                                                                    "cancelDate = ?," + "status = ?," + "WHERE number = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM account WHERE number = ?";
    private static final String SELECT_ACCOUNT_USER_ID = "SELECT * FROM account WHERE userID = ?";
    private static final String SELECT_ACCOUNT_NUMBER = "SELECT * FROM account WHERE number = ?";
    private static final String SELECT_ACCOUNT_USERNAME = "SELECT * FROM account WHERE username = ?";
    private static final String SELECT_ALL = "SELECT * FROM account";

    public void insertAccount(Account newAccount) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT)) {
            preparedStatement.setInt(1, newAccount.getNumber());
            preparedStatement.setString(2, newAccount.getUsername());
            preparedStatement.setString(3, newAccount.getPassword());
            preparedStatement.setInt(4, newAccount.getType());
            preparedStatement.setDouble(5, newAccount.getBalance());
            preparedStatement.setDate(6, new java.sql.Date(newAccount.getOpenDate().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(newAccount.getCancelDate().getTime()));
            preparedStatement.setInt(8, newAccount.getStatus());
            preparedStatement.setInt(9, newAccount.getUserID());
            preparedStatement.executeUpdate();
            System.out.println("Nueva cuenta insertada exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cuenta: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public void updateAccount(int number, Account newAccount) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            preparedStatement.setInt(1, newAccount.getNumber());
            preparedStatement.setString(2, newAccount.getUsername());
            preparedStatement.setString(3, newAccount.getPassword());
            preparedStatement.setInt(4, newAccount.getType());
            preparedStatement.setDouble(5, newAccount.getBalance());
            preparedStatement.setDate(6, new java.sql.Date(newAccount.getOpenDate().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(newAccount.getCancelDate().getTime()));
            preparedStatement.setInt(8, newAccount.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("datos de cuenta actualizados exitosamente a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos de cuenta: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public void deleteAccount(int number) {
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT)) {
            preparedStatement.setInt(1, number);
            preparedStatement.execute();
            System.out.println("Cuenta eliminada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cuenta con numero " + number + ":" + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public Account selectAccountByNumber(int number) {
        Account account = null;
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_NUMBER)) {
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                account = new Account();
                account.setNumber(number);
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setType(resultSet.getInt("type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setOpenDate(resultSet.getDate("openDate"));
                account.setCancelDate(resultSet.getDate("cancelDate"));
                account.setStatus(resultSet.getInt("status"));
                account.setUserID(resultSet.getInt("userID"));
                
                System.out.println("Cuenta encontrado correctamente por su numero");
            }
        } catch (SQLException e) {
            System.out.println("Cuenta no encontrada " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }

        return account;
    }

    public Account selectAccountByUserID(int userID) {
        Account account = null;
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_USER_ID)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                account = new Account();
                account.setNumber(resultSet.getInt("number"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setType(resultSet.getInt("type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setOpenDate(resultSet.getDate("openDate"));
                account.setCancelDate(resultSet.getDate("cancelDate"));
                account.setStatus(resultSet.getInt("status"));
                account.setUserID(resultSet.getInt("userID"));
                
                System.out.println("Cuenta encontrado correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Cuenta no encontrada " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }

        return account;
    }
    
    public boolean userNameInUse(String username) {
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Usuario encontrado exitosamente, username en uso");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al usuario: " + e.getMessage());
            return true;
        } finally {
            DBConnection.closeConnection(connection);
        }
        
        return false;
    }
    

    public Account selectAccountByUsername(String username){
        Account account = null;
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = new Account();
                account.setNumber(resultSet.getInt("number"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setType(resultSet.getInt("type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setOpenDate(resultSet.getDate("openDate"));
                account.setCancelDate(resultSet.getDate("cancelDate"));
                account.setStatus(resultSet.getInt("status"));
                account.setUserID(resultSet.getInt("userID"));
                System.out.println("Cuenta encontrado exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la cuenta: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }

        return account;
    }

    // Se obtienen los datos de todas las personas registradas
    public List<Account> selectAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                Account account = new Account();
                account.setNumber(resultSet.getInt("number"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setType(resultSet.getInt("type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setOpenDate(resultSet.getDate("openDate"));
                account.setCancelDate(resultSet.getDate("cancelDate"));
                account.setStatus(resultSet.getInt("status"));
                account.setUserID(resultSet.getInt("userID"));
                accounts.add(account);
            }
    
            System.out.println("Lista de cuentas recuperada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al seleccionar lista de cuentas: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    
        return accounts;
    }
}
