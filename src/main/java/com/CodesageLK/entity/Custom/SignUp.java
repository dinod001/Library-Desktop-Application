package com.CodesageLK.entity.Custom;

public class SignUp {
    String UserName;
    String Password;
    String Email;

    public SignUp(String userName, String password, String email) {
        UserName = userName;
        Password = password;
        Email = email;
    }

    public SignUp() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
