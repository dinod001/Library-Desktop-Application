package com.CodesageLK.service.Custom;

import com.CodesageLK.dto.Impl.BookDTO;
import com.CodesageLK.service.CrudService;
import com.CodesageLK.utill.exception.SuperException;

public interface BookService extends CrudService<BookDTO,Integer> {
    public BookDTO seacrh(int bookID) throws SuperException;
}
