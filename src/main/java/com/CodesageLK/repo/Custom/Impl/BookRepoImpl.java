package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Book;
import com.CodesageLK.repo.Custom.BookRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepoImpl implements BookRepo {
    @Override
    public boolean add(Book book) throws SQLException, ClassNotFoundException {
        String sql = "insert into book(id,name,price,isbn,publisher_id,main_category_id) values(?,?,?,?,?,?)";
        boolean executedSql=CrudUtil.executeSql(sql);
        return executedSql;
    }

    @Override
    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        String sql="UPDATE book set name=?,price=?,isbn=?,publisher_id=?,main_category_id=? where id=?";
        boolean executedSQL=CrudUtil.executeSql(sql,book.getName(),book.getPrice(),book.getIsbn(),book.getPublisherId(),book.getMainCategoryId());
        return executedSQL;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql = "delete from book where id=?";
        boolean executedSQL=CrudUtil.executeSql(sql,t);
        return executedSQL;
    }

    @Override
    public Optional<Book> get(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "select * from book where id=?";
        ResultSet rs = CrudUtil.executeSql(sql, integer);
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setPrice(rs.getFloat(("price")));
            book.setIsbn(rs.getString("isbn"));
            book.setPublisherId(rs.getString("publisher_id"));
            book.setMainCategoryId(rs.getString("main_category_id"));
            return Optional.of(book);

        }
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from book";
        ResultSet rs = CrudUtil.executeSql(sql);
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setPrice(rs.getFloat("price"));
            book.setIsbn(rs.getString("isbn"));
            book.setPublisherId(rs.getString("publisher_id"));
            book.setMainCategoryId(rs.getString("main_category_id"));
            books.add(book);
        }
        return books;
    }
}
