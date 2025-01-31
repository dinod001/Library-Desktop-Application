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
    public boolean update(SignUp signUp) throws SQLException, ClassNotFoundException {
        String sql = "update admin set password=? where username=?";
        boolean result= CrudUtil.executeSql(sql,signUp.getPassword(),signUp.getUserName());
        if (result) return true;
        else return false;
    }

    @Override
    public boolean search(String username,String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin where username=? and password=?";
        ResultSet rs = CrudUtil.executeSql(sql,username,password);
        if (rs != null && rs.next()) {
            return true;  // Username exists
        } else {
            return false;  // No match found
        }
    }
}
