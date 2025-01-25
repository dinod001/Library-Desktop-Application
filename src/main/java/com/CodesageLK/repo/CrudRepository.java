package com.CodesageLK.repo;

import com.CodesageLK.entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository <T extends SuperEntity,ID>{
    boolean add(T t) throws SQLException,ClassNotFoundException;
    boolean update(T t) throws SQLException,ClassNotFoundException;
    boolean delete(ID t) throws SQLException,ClassNotFoundException;
    Optional<T> get(ID id) throws SQLException,ClassNotFoundException;
    List<T> getAll() throws SQLException,ClassNotFoundException;
}
