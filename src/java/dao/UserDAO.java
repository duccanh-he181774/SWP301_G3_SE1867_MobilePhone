/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author LENOVO
 */
import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.User;
import model.UserWithRole;

public class UserDAO {

    private DBContext dbContext = new DBContext();
    
    public boolean updateUser(UserWithRole user) throws SQLException, Exception {
    String sql = "UPDATE Users SET UserName = ?, Email = ?, RegistrationDate = ?, Address = ? WHERE UserID = ?";
    try (Connection connection = dbContext.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getEmail());
        statement.setTimestamp(3, (Timestamp) user.getRegistrationDate());
        statement.setString(4, user.getAddress());
        statement.setInt(5, user.getUserID());
        
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }
}
    
    public UserWithRole getUserById(int userId) throws SQLException, Exception {
    String sql = "SELECT u.UserID, u.UserName, u.Email, u.RegistrationDate, u.Address, r.RoleName " +
                 "FROM Users u " +
                 "LEFT JOIN UserRoles ur ON u.UserID = ur.UserID " +
                 "LEFT JOIN Roles r ON ur.RoleID = r.RoleID " +
                 "WHERE u.UserID = ?";
    try (Connection connection = dbContext.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setInt(1, userId);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new UserWithRole(
                    resultSet.getInt("UserID"),
                    resultSet.getString("UserName"),
                    resultSet.getString("Email"),
                    resultSet.getTimestamp("RegistrationDate"),
                    resultSet.getString("Address"),
                    resultSet.getString("RoleName")
                );
            }
        }
    }
    return null; // Trả về null nếu không tìm thấy người dùng
}



    public List<User> getAllUsers() throws SQLException, Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection connection = dbContext.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("UserName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getTimestamp("RegistrationDate"),
                        resultSet.getString("Address"),
                        resultSet.getString("PaymentInfo")
                );
                users.add(user);
            }
        }
        return users;
    }

    public List<UserWithRole> getUsersWithRoles() throws SQLException, Exception {
        List<UserWithRole> usersWithRoles = new ArrayList<>();
        String sql = "SELECT u.UserID, u.UserName, u.Email, u.RegistrationDate, u.Address, r.RoleName "
                + "FROM Users u "
                + "LEFT JOIN UserRoles ur ON u.UserID = ur.UserID "
                + "LEFT JOIN Roles r ON ur.RoleID = r.RoleID";

        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UserWithRole userWithRole = new UserWithRole(
                        resultSet.getInt("UserID"),
                        resultSet.getString("UserName"),
                        resultSet.getString("Email"),
                        resultSet.getTimestamp("RegistrationDate"),
                        resultSet.getString("Address"),
                        resultSet.getString("RoleName")
                );
                usersWithRoles.add(userWithRole);
            }
        }
        return usersWithRoles;
    }
    
    public boolean deleteUser(int userId) throws SQLException, Exception {
    String sql = "DELETE FROM Users WHERE UserID = ?";
    try (Connection connection = dbContext.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setInt(1, userId);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    }
}


}