/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class User {
    public int userID;
    public String username;
    public String email;
    public String password;
    public Date regisDate;
    public String address;
    public String paymentInfor;

    public User() {
    }

    public User(int userID, String username, String email, String password, Date regisDate, String address, String paymentInfor) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.regisDate = regisDate;
        this.address = address;
        this.paymentInfor = paymentInfor;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Date regisDate) {
        this.regisDate = regisDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentInfor() {
        return paymentInfor;
    }

    public void setPaymentInfor(String paymentInfor) {
        this.paymentInfor = paymentInfor;
    }
    
}


