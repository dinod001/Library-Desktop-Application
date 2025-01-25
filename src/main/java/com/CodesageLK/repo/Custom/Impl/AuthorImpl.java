package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Author;
import com.CodesageLK.repo.Custom.AuthorRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorImpl implements AuthorRepo {
    @Override
    public boolean add(Author author) throws SQLException, ClassNotFoundException {
        String sql = "insert into author(id,name,contact) values(?,?,?)";
        boolean executedSql=CrudUtil.executeSql(sql, author.getId(), author.getName(), author.getContact());
        return executedSql;
    }

    @Override
    public boolean update(Author author) throws SQLException, ClassNotFoundException {
        String sql = "update author set name=?,contact=? where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, author.getName(), author.getContact(), author.getId());
        return executedSql;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql = "delete from author where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, t);
        return executedSql;
    }

    @Override
    public Optional<Author> get(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "select * from author where id=?";
        ResultSet rs=CrudUtil.executeSql(sql, integer);
        if(rs.next()){
            Author author=new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setContact(rs.getString("contact"));
            return Optional.of(author);
        }
        return Optional.empty();
    }

    @Override
    public List<Author> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from author";
        ResultSet rs=CrudUtil.executeSql(sql);
        List<Author> authors=new ArrayList<>();
        while(rs.next()){
            Author author=new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setContact(rs.getString("contact"));
            authors.add(author);
        }
        return authors;
    }
}
