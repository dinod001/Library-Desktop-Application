package com.CodesageLK.repo.Custom;

import com.CodesageLK.entity.Custom.SignUp;

import java.sql.SQLException;

public interface SignUpRepo {
    public boolean save(SignUp signUp)throws SQLException, ClassNotFoundException;
    public boolean update(String username,String password)throws SQLException, ClassNotFoundException;
    public int search(String username,String password)throws SQLException, ClassNotFoundException;
    public boolean usernameExists(String username)throws SQLException, ClassNotFoundException;
}
