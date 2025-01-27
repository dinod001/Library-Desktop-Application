package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.Book;
import com.CodesageLK.repo.Custom.BookRepo;
import com.CodesageLK.utill.CrudUtil;
import com.CodesageLK.utill.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepoImpl implements BookRepo {
    @Override
    public boolean add(Book book) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO book(name, price, isbn, publisher_id, main_category_id) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setDouble(2, book.getPrice());
        preparedStatement.setString(3, book.getIsbn());
        preparedStatement.setInt(4, Integer.parseInt(book.getPublisherId()));
        preparedStatement.setInt(5, Integer.parseInt(book.getMainCategoryId()));

        // Execute the update
        boolean result = preparedStatement.executeUpdate() > 0;

        // Retrieve the generated key (auto-incremented ID)
        if (result) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // Get the auto-generated ID
                    book.setId(generatedId);                  // Set the ID in the Book object
                    System.out.println("Generated ID: " + generatedId);
                }
            }
        }
        return result;
    }

    @Override
    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        String sql="UPDATE book set name=?,price=?,isbn=?,publisher_id=?,main_category_id=? where id=?";
        boolean executedSQL=CrudUtil.executeSql(sql,book.getName(),book.getPrice(),book.getIsbn(),book.getPublisherId(),book.getMainCategoryId(),book.getId());
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
