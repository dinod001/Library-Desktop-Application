package com.CodesageLK.dto.Impl;

import com.CodesageLK.dto.SuperDTO;

public class SignUpDto implements SuperDTO {
    String UserName;
    String Password;
    String Email;

    public SignUpDto(String userName, String password, String email) {
        UserName = userName;
        Password = password;
        Email = email;
    }

    public SignUpDto() {
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
