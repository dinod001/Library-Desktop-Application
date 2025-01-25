package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Publisher;
import com.CodesageLK.repo.Custom.PublisherRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherImpl implements PublisherRepo {
    @Override
    public boolean add(Publisher publisher) throws SQLException, ClassNotFoundException {
        String sql = "insert into publisher(id,name,location,contact) values(?,?,?,?)";
        boolean executedSql=CrudUtil.executeSql(sql, publisher.getId(), publisher.getName(), publisher.getLocation(), publisher.getContact());
        return executedSql;
    }

    @Override
    public boolean update(Publisher publisher) throws SQLException, ClassNotFoundException {
        String sql = "update publisher set name=?,location=?,contact=? where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, publisher.getName(), publisher.getLocation(), publisher.getContact(), publisher.getId());
        return executedSql;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql = "delete from publisher where id=?";
        boolean executedSql=CrudUtil.executeSql(sql, t);
        return executedSql;
    }

    @Override
    public Optional<Publisher> get(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "select * from publisher where id=?";
        ResultSet rs = CrudUtil.executeSql(sql, integer);
        if (rs.next()) {
            Publisher publisher = new Publisher();
            publisher.setId(rs.getInt("id"));
            publisher.setName(rs.getString("name"));
            publisher.setLocation(rs.getString("location"));
            publisher.setContact(rs.getString("contact"));
            return Optional.of(publisher);
        }
        return Optional.empty();
    }

    @Override
    public List<Publisher> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from publisher";
        ResultSet rs = CrudUtil.executeSql(sql);
        List<Publisher> publishers = new ArrayList<>();
        while (rs.next()) {
            Publisher publisher = new Publisher();
            publisher.setId(rs.getInt("id"));
            publisher.setName(rs.getString("name"));
            publisher.setLocation(rs.getString("location"));
            publisher.setContact(rs.getString("contact"));
            publishers.add(publisher);
        }
        return publishers;
    }
}
