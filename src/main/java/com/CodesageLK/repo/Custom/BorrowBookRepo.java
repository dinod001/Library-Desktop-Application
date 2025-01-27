package com.CodesageLK.repo.Custom;

import com.CodesageLK.dto.Impl.BorrowBookDTO;
import com.CodesageLK.utill.exception.SuperException;

import java.util.List;

public interface BorrowBookRepo{
    public boolean addBorrowBook(List<BorrowBookDTO> borrowBookDTOList) throws SuperException;
    public List<BorrowBookDTO> getBorrowBookList() throws SuperException;
}
