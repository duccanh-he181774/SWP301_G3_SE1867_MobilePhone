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
import model.User;
import model.UserWithRole;

public class UserDAO {

    private DBContext dbContext = new DBContext();

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

    public static void main(String[] args) throws Exception {
        UserDAO dao = new UserDAO();

        System.out.println(dao.getAllUsers());
    }

    public UserWithRole getUserById(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateUser(UserWithRole user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
