package com.CodesageLK.service;

import com.CodesageLK.dto.SuperDTO;
import com.CodesageLK.utill.exception.SuperException;

import java.util.List;
import java.util.Optional;

public interface CrudService <T extends SuperDTO,ID> extends SuperService{
    boolean add(T t) throws SuperException;
    boolean update(T t) throws SuperException;
    boolean delete(ID t) throws SuperException;
    Optional<T> get(ID id) throws SuperException;
    List<T> getAll() throws SuperException;
}
