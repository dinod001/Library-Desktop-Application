package com.CodesageLK.repo.Custom;

import com.CodesageLK.entity.Custom.BookAuthor;
import com.CodesageLK.repo.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface BookAuthorRepo extends CrudRepository<BookAuthor, Integer> {
    public boolean saveList(List<BookAuthor> bookAuthor) throws SQLException, ClassNotFoundException;
    public List<Integer> search(int bookId) throws SQLException, ClassNotFoundException;
}
