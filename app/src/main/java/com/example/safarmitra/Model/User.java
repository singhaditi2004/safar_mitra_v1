package com.example.safarmitra.Model;

public class User {
    private String userName;
    private String userPassword;
    private String phoneNo;
    private String email;

    public User(String userName, String userPassword, String phoneNo, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
