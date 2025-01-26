package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Category;
import com.CodesageLK.repo.Custom.CategoryRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryImpl implements CategoryRepo {
    @Override
    public boolean add(Category category) throws SQLException, ClassNotFoundException {
        String sql = "insert into category(id,name)values(?,?)";
        boolean executedSql=CrudUtil.executeSql(sql, category.getId(), category.getName());
        return executedSql;
    }

    @Override
    public boolean update(Category category) throws SQLException, ClassNotFoundException {
        String sql = "update category set name=? where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, category.getName(), category.getId());
        return executedSql;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql = "delete from category where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, t);
        return executedSql;
    }

    @Override
    public Optional<Category> get(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "select * from category where id=?";
        ResultSet rs=CrudUtil.executeSql(sql, integer);
        if(rs.next()){
            Category category=new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return Optional.of(category);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from category";
        ResultSet rs=CrudUtil.executeSql(sql);
        List<Category> list=new ArrayList<>();
        while (rs.next()){
            Category category=new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            list.add(category);
        }
        return list;
    }
}
