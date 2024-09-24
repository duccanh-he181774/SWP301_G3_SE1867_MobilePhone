package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    /**
     * Authenticates a user by email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The authenticated user, or null if the credentials are invalid.
     */
    public User authenticateUser(String email, String password) {
        try {
            String sql = "SELECT * FROM Users WHERE (email = ? or UserName = ?) AND passwordHash = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, email);
            stmt.setString(3, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("userID");
                String userName = rs.getString("userName");
                String passwordHash = rs.getString("passwordHash");
                Date registrationDate = rs.getDate("registrationDate");
                String address = rs.getString("address");
                String paymentInfo = rs.getString("paymentInfo");

                return new User(userID, userName, email, passwordHash, registrationDate, address, paymentInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Registers a new user.
     *
     * @param userName The username of the new user.
     * @param email The email of the new user.
     * @param password The password of the new user.
     * @param address The address of the new user.
     * @param paymentInfo The payment information of the new user.
     * @param gender The gender of the new user.
     * @param mobile The mobile number of the new user.
     * @return The newly created user, or null if the registration failed.
     */
    public User registerUser(String userName, String email, String password, String address, String paymentInfo) {
        try {
            String sql = "INSERT INTO Users (userName, email, passwordHash, registrationDate, address, paymentInfo) VALUES (?, ?, ?, GETDATE(), ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, address);
            stmt.setString(5, paymentInfo);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);
                    return new User(userID, userName, email, password, new Date(), address, paymentInfo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Resets the password for a user.
     *
     * @param email The email of the user.
     * @param newPassword The new password for the user.
     * @return True if the password was reset successfully, false otherwise.
     */
    public boolean resetPassword(String email, String newPassword) {
        try {
            String sql = "UPDATE Users SET passwordHash = ? WHERE email = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Registers a new user with email verification.
     *
     * @param userName The username of the new user.
     * @param email The email of the new user.
     * @param password The password of the new user.
     * @param address The address of the new user.
     * @param paymentInfo The payment information of the new user.
     * @param gender The gender of the new user.
     * @param mobile The mobile number of the new user.
     * @return The newly created user, or null if the registration failed.
     */
    public User registerUserWithEmailVerification(String userName, String email, String password, String address, String paymentInfo, String gender, String mobile) {
        try {
            String sql = "INSERT INTO Users (userName, email, passwordHash, registrationDate, address, paymentInfo, gender, mobile, isVerified) VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?, 0)";
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, address);
            stmt.setString(5, paymentInfo);
            stmt.setString(6, gender);
            stmt.setString(7, mobile);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);
                    // Send email verification to the user
                    sendEmailVerification(email);
                    return new User(userID, userName, email, password, new Date(), address, paymentInfo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Verifies the email of a user.
     *
     * @param email The email of the user.
     * @return True if the email was verified successfully, false otherwise.
     */
    public boolean verifyEmail(String email) {
        try {
            String sql = "UPDATE Users SET isVerified = 1 WHERE email = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Sends an email verification to the user.
     *
     * @param email The email of the user.
     */
    private void sendEmailVerification(String email) {
        // Code to send email verification goes here
        System.out.println("Email verification sent to: " + email);
    }
}
