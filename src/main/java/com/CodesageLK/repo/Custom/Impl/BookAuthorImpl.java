package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.BookAuthor;
import com.CodesageLK.repo.Custom.BookAuthorRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookAuthorImpl implements BookAuthorRepo {

    boolean flag = true;
    @Override
    public boolean saveList(List<BookAuthor> bookAuthor) throws SQLException, ClassNotFoundException {
        for (BookAuthor bookAuthor1 : bookAuthor) {
            if (flag){
                add(bookAuthor1);
            }
            else{
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(BookAuthor bookAuthor) throws SQLException, ClassNotFoundException {
        String sql = "insert into book_author(book_id,author_id) values(?,?)";
        flag=CrudUtil.executeSql(sql,bookAuthor.getBookID(),bookAuthor.getAuthorID());
        return flag;
    }

    @Override
    public boolean update(BookAuthor bookAuthor) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql = "delete from book_author where book_id=?";
        return CrudUtil.executeSql(sql,t);
    }

    @Override
    public Optional<BookAuthor> get(Integer integer) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<BookAuthor> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

}
