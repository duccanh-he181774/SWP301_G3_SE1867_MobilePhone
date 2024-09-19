package model;


import java.util.Date;

public class UserWithRole {
    private int userID;
    private String userName;
    private String email;
    private Date registrationDate;
    private String address;
    private String roleName;

    // Constructors
    public UserWithRole() {
    }

    public UserWithRole(int userID, String userName, String email, Date registrationDate, String address, String roleName) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.address = address;
        this.roleName = roleName;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserWithRole{" + "userID=" + userID + ", userName=" + userName + ", email=" + email + ", registrationDate=" + registrationDate + ", address=" + address + ", role=" + roleName + '}';
    }
    
    
}
