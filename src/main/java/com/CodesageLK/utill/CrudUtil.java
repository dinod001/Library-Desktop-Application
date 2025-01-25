package com.CodesageLK.utill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T executeSql(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        if (sql.toLowerCase().startsWith("select")) {
           ResultSet rs= preparedStatement.executeQuery();
           return (T) rs;
        }
        Boolean result=preparedStatement.executeUpdate()>0;
        return (T) result;
    }
}
