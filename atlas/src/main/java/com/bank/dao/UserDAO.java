package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.User;

public class UserDAO {

    private static final String INSERT_USER = "INSERT INTO user (firstName," + "middleName," + "lastName1," + "lastName2," + 
                                                                    "birthDate," + "adress," + "phoneNumber," + "email," +
                                                                    "identification," + "isAdmin) " + "VALUES " +
                                                                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET firstName = ?," + "middleName = ?," + "lastName1 = ?," +
                                                                    "lastName2 = ?," + "birthDate = ?," + "adress = ?," +
                                                                    "phoneNumber = ?," + "email = ?," + "identification = ?," +
                                                                    "isAdmin = ?," + "WHERE userID = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE userID = ?";
    private static final String SELECT_USER_ID = "SELECT * FROM user WHERE userID = ?";
    private static final String SELECT_USER_IDENTIFICATION = "SELECT * FROM user WHERE identification = ?";
    private static final String SELECT_ALL = "SELECT * FROM user";
    
    public void insertUser(User newUser) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getMiddleName());
            preparedStatement.setString(3, newUser.getLastName1());
            preparedStatement.setString(4, newUser.getLastName2());
            preparedStatement.setDate(5, new java.sql.Date(newUser.getBirthDate().getTime()));
            preparedStatement.setString(6, newUser.getAddress());
            preparedStatement.setString(7, newUser.getPhoneNumber());
            preparedStatement.setString(8, newUser.getEmail());
            preparedStatement.setString(9, newUser.getIdentification());
            preparedStatement.setBoolean(10, newUser.isAdmin());
            preparedStatement.executeUpdate();
            System.out.println("Nueva usuario insertada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public void updateUser(int userID, User newUser) {
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getMiddleName());
            preparedStatement.setString(3, newUser.getLastName1());
            preparedStatement.setString(4, newUser.getLastName2());
            preparedStatement.setDate(5, new java.sql.Date(newUser.getBirthDate().getTime()));
            preparedStatement.setString(6, newUser.getAddress());
            preparedStatement.setString(7, newUser.getPhoneNumber());
            preparedStatement.setString(8, newUser.getEmail());
            preparedStatement.setString(9, newUser.getIdentification());
            preparedStatement.setBoolean(10,newUser.isAdmin());
            preparedStatement.setInt(11, userID);
            preparedStatement.executeUpdate();
            System.out.println("datos usuario actualizados exitosamente a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos de usuario: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public void deleteUser(int userID) {
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.execute();
            System.out.println("Usuario eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario con ID " + userID + ":" + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public User selectUserByID(int userID) {
        User user = null;
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("userID"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setMiddleName(resultSet.getString("middleName"));
                user.setLastName1(resultSet.getString("lastName1"));
                user.setLastName2(resultSet.getString("lastName2"));
                user.setBirthDate(resultSet.getDate("birthDate"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setEmail(resultSet.getString("email"));
                user.setIdentification(resultSet.getString("identification"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                
                System.out.println("Usuario encontrado correctamente por su ID");
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar un usuario por ID: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }

        return user;
    }

    public boolean userExists(String identification) {
        boolean exists = false;
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_IDENTIFICATION)) {
            preparedStatement.setString(1, identification);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            exists = resultSet.next();
            
            if (exists) {
                System.out.println("Usuario identificado correctamente.");
            } else {
                System.out.println("No se encontró ningún usuario con la identificación proporcionada.");
            }
            return exists;
        } catch (SQLException e) {
            System.out.println("Error al identificar el usuario por número de identificación: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
        
        return exists;
    }

    // Retorna el primer personID de una persona cuyo documento es el indicado
    // Esta función se usa de modo que se garantiza que el documento es único (constructor por fecha)
    public int getUserByIdentification(String identification) {
        int id = -1;
        Connection connection = DBConnection.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_IDENTIFICATION)) {
            preparedStatement.setString(1, identification);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getInt("userID");
                System.out.println("Usuario identificado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al identificar el usuario por número de identificación: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
        
        return id;
    }
    

    // Se retorna una persona (o varias personas) persona de acuerdo a su documento de identidad
    public List<User> selectPersonByDocument(String document) {
        List<User> users = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_IDENTIFICATION)) {
            preparedStatement.setString(1, document);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("userID"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setMiddleName(resultSet.getString("middleName"));
                user.setLastName1(resultSet.getString("lastName1"));
                user.setLastName2(resultSet.getString("lastName2"));
                user.setBirthDate(resultSet.getDate("birthDate"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setEmail(resultSet.getString("email"));
                user.setIdentification(resultSet.getString("identification"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                users.add(user);
            }
    
            System.out.println("Lista de usuarios recuperada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al seleccionar lista de usuarios: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    
        return users;
    }
    

    // Se obtienen los datos de todas las personas registradas
    public List<User> selectAllPersons() {
        List<User> users = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("userID"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setMiddleName(resultSet.getString("middleName"));
                user.setLastName1(resultSet.getString("lastName1"));
                user.setLastName2(resultSet.getString("lastName2"));
                user.setBirthDate(resultSet.getDate("birthDate"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setEmail(resultSet.getString("email"));
                user.setIdentification(resultSet.getString("identification"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                users.add(user);
            }
    
            System.out.println("Lista de usuarios recuperada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al seleccionar lista de usuarios: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection);
        }
    
        return users;
    }
    
}
