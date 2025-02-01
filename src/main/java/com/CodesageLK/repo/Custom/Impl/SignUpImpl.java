package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.SignUp;
import com.CodesageLK.repo.Custom.SignUpRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpImpl implements SignUpRepo {
    @Override
    public boolean save(SignUp signUp) throws SQLException, ClassNotFoundException {
        String sql = "insert into admin(username,email,password) values(?,?,?)";
        boolean result= CrudUtil.executeSql(sql,signUp.getUserName(),signUp.getEmail(),signUp.getPassword());
        if (result) return true;
        else return false;
    }

    @Override
    public boolean update(String username,String password) throws SQLException, ClassNotFoundException {
        String sql = "update admin set password=? where username=?";
        boolean result= CrudUtil.executeSql(sql,password,username);
        if (result) return true;
        else return false;
    }

    @Override
    public int search(String username,String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin where username=? and password=?";
        ResultSet rs = CrudUtil.executeSql(sql,username,password);
        if (rs != null && rs.next()) {
            return rs.getInt("id");  // Username exists
        } else {
            return 0;  // No match found
        }
    }

    @Override
    public boolean usernameExists(String username) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin where username=?";
        ResultSet rs = CrudUtil.executeSql(sql,username);
        if (rs != null && rs.next()) {
            return true;
        }
        return false;
    }
}
