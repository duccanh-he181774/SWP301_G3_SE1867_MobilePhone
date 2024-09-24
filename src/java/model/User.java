package model;

import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String email;
    private String passwordHash;
    private Date registrationDate;
    private String address;
    private String paymentInfo;

    public User() {
    }

    public User(int userID, String userName, String email, String passwordHash, Date registrationDate, String address, String paymentInfo) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registrationDate = registrationDate;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
