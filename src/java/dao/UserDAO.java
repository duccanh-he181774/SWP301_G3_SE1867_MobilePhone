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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.UserWithRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends  DBContext{

    private DBContext dbContext = new DBContext();

    public User authenticateUser(String email, String password) {
        String sql = "SELECT * FROM Users WHERE (email = ? OR UserName = ?) AND passwordHash = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Mã hóa mật khẩu trước khi so sánh với passwordHash trong DB
            String hashedPassword = hashPassword(password); // Giả sử bạn có một hàm hashPassword để băm mật khẩu

            stmt.setString(1, email);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String userName = rs.getString("userName");
                    String passwordHash = rs.getString("passwordHash");
                    Date registrationDate = rs.getDate("registrationDate");
                    String address = rs.getString("address");
                    String paymentInfo = rs.getString("paymentInfo");

                    return new User(userID, userName, email, passwordHash, registrationDate, address, paymentInfo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User registerUser(String userName, String email, String password, String address, String paymentInfo) {
        String sql = "INSERT INTO Users (userName, email, passwordHash, registrationDate, address, paymentInfo) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";

        try (Connection connection = dbContext.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Mã hóa mật khẩu trước khi lưu
            String hashedPassword = hashPassword(password); // Hàm hashPassword để băm mật khẩu

            stmt.setString(1, userName);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, address);
            stmt.setString(5, paymentInfo);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userID = generatedKeys.getInt(1);
                        return new User(userID, userName, email, hashedPassword, new Date(), address, paymentInfo);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean resetPassword(String email, String newPassword) {
        String sql = "UPDATE Users SET passwordHash = ? WHERE email = ?";

        try (Connection connection = dbContext.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Băm mật khẩu mới trước khi lưu vào cơ sở dữ liệu
            String hashedPassword = hashPassword(newPassword);

            stmt.setString(1, hashedPassword);
            stmt.setString(2, email);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;  // Trả về true nếu có ít nhất một dòng bị ảnh hưởng (cập nhật thành công)

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;  // Trả về false nếu có lỗi xảy ra
    }

    private String hashPassword(String password) {
        // Sử dụng một hàm băm như SHA-256, BCrypt, v.v. để mã hóa mật khẩu
        // Đây là ví dụ sử dụng SHA-256
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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

    public User registerUserWithEmailVerification(String userName, String email, String password, String address, String paymentInfo, String gender, String mobile) {
        // Hash the password before storing it
        String passwordHash = hashPassword(password);

        try {
            String sql = "INSERT INTO Users (userName, email, passwordHash, registrationDate, address, paymentInfo, gender, mobile, isVerified) VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?, 0)";
            // Use dbContext to get connection
            try (Connection connection = dbContext.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, userName);
                stmt.setString(2, email);
                stmt.setString(3, passwordHash);  // Store hashed password
                stmt.setString(4, address);
                stmt.setString(5, paymentInfo);
                stmt.setString(6, gender);
                stmt.setString(7, mobile);

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int userID = generatedKeys.getInt(1);
                            // Send email verification to the user
                            sendEmailVerification(email);
                            return new User(userID, userName, email, passwordHash, new Date(), address, paymentInfo);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    private void sendEmailVerification(String email) {
        // Code to send email verification goes here
        System.out.println("Email verification sent to: " + email);
    }

}
