package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Member;
import com.CodesageLK.repo.Custom.MemberRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepoImpl implements MemberRepo {

    @Override
    public boolean add(Member member) throws SQLException, ClassNotFoundException {
        String sql="insert into member(id,name,address,email,contact) values(?,?,?,?,?)";
        boolean execute=CrudUtil.executeSql(sql,member.getId(),member.getName(),member.getAddress(),member.getEmail(),member.getContact());
        return execute;
    }

    //Update member
    @Override
    public boolean update(Member member) throws SQLException, ClassNotFoundException {
        String sql="UPDATE member set name=?,address=?,email=?,contact=? where id=?";
        boolean execute=CrudUtil.executeSql(sql,member.getName(),member.getAddress(),member.getEmail(),member.getContact(),member.getId());
        return execute;
    }

    //delete member
    @Override
    public boolean delete(String memmberId) throws SQLException, ClassNotFoundException {
        String sql="delete from member where id=?";
        boolean execute=CrudUtil.executeSql(sql,memmberId);
        return execute;
    }

    //seacrh member
    @Override
    public Optional<Member> get(String id) throws SQLException, ClassNotFoundException {
        String sql="select * from member where id=?";
        ResultSet rs = CrudUtil.executeSql(sql,id);
        if (rs.next()) {
            Member member=new Member();
            member.setId(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAddress(rs.getString(3));
            member.setEmail(rs.getString(4));
            member.setContact(rs.getString(5));
            return Optional.of(member);
        }
        return Optional.empty();
    }

    //get all
    @Override
    public List<Member> getAll() throws SQLException, ClassNotFoundException {
        String sql="select * from member";
        ResultSet rs=CrudUtil.executeSql(sql);
        List<Member> members=new ArrayList<>();
        while (rs.next()){
            Member list=new Member();
            list.setId(rs.getString(1));
            list.setName(rs.getString(2));
            list.setAddress(rs.getString(3));
            list.setEmail(rs.getString(4));
            list.setContact(rs.getString(5));
            members.add(list);
        }
        return members;
    }
}
