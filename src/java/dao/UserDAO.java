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

    public UserWithRole getUserById(int userId) throws SQLException, Exception {
        UserWithRole userWithRole = null;
        String sql = "SELECT u.UserID, u.UserName, u.Email, u.RegistrationDate, u.Address, r.RoleName "
                + "FROM Users u "
                + "LEFT JOIN UserRoles ur ON u.UserID = ur.UserID "
                + "LEFT JOIN Roles r ON ur.RoleID = r.RoleID "
                + "WHERE u.UserID = ?";

        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userWithRole = new UserWithRole(
                        resultSet.getInt("UserID"),
                        resultSet.getString("UserName"),
                        resultSet.getString("Email"),
                        resultSet.getTimestamp("RegistrationDate"),
                        resultSet.getString("Address"),
                        resultSet.getString("RoleName")
                );
            }
        }
        return userWithRole;
    }

    public boolean deleteUser(int userId) throws SQLException, Exception {
        // Xóa bản ghi trong bảng UserRoles trước
        String deleteUserRolesSql = "DELETE FROM UserRoles WHERE UserID = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteUserRolesSql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }

        // Sau đó xóa người dùng
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Nếu có ít nhất một hàng bị xóa, trả về true
        }
    }

    public boolean updateUser(UserWithRole user) throws SQLException, Exception {
        String sql = "UPDATE Users SET UserName = ?, Email = ?, Address = ? WHERE UserID = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getAddress());
            statement.setInt(4, user.getUserID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Nếu có ít nhất một hàng bị cập nhật, trả về true
        }
    }

    public static void main(String[] args) {
        // Giả sử bạn đã có dbContext được khởi tạo
        UserDAO userdao = new UserDAO();

        int userIdToDelete = 3; // Thay đổi ID người dùng theo ý bạn

        try {
            boolean isDeleted = userdao.deleteUser(userIdToDelete);
            if (isDeleted) {
                System.out.println("User with ID " + userIdToDelete + " was deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userIdToDelete + ".");
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}
