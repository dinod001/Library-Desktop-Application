package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.BookDTO;
import com.CodesageLK.entity.Custom.Book;
import com.CodesageLK.utill.exception.Custom.BookException;
import com.CodesageLK.utill.exception.SuperException;
import com.CodesageLK.repo.Custom.BookRepo;
import com.CodesageLK.repo.Custom.Impl.BookRepoImpl;
import com.CodesageLK.service.Custom.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookRepo repo;
    public BookServiceImpl(BookRepoImpl bookRepo) {
        this.repo = bookRepo;
    }
    @Override
    public boolean add(BookDTO bookDTO) throws SuperException {
        Book book=this.convertDtoToEntity(bookDTO);
        try {
            return repo.add(book);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new BookException("Duplicate Member ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new BookException("input data is too log in "+errors[1],e);
                }
            }
            throw new BookException("Error occurred - Contact Developer",e);
        }
    }

    @Override
    public boolean update(BookDTO bookDTO) throws SuperException {
        Book  book=this.convertDtoToEntity(bookDTO);
        try {
            return repo.update(book);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (((SQLException)e).getErrorCode() == 1406){
                String[] errors=((SQLException)e).getMessage().split("'");
                throw new BookException("input data is too log in "+errors[1],e);
            }
            throw new BookException("Error Ocurred - Contact Developer",e);
        }
    }

    @Override
    public boolean delete(Integer t) throws BookException {
        try {
            return repo.delete(t);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new BookException("Something went wrong - contact Developer or not implemented yet",e);
        }
    }

    @Override
    public Optional<BookDTO> get(Integer integer) throws BookException {
        try {
            Optional<Book> result=repo.get(integer);
            if(result.isPresent()){
                BookDTO bookDTO=this.convertEntityToDto(result.get());
                return Optional.of(bookDTO);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new BookException("Something went wrong");
        }
        return Optional.empty();
    }

    @Override
    public List<BookDTO> getAll() throws SuperException {
        try {
            List<Book> books=repo.getAll();
            ArrayList<BookDTO> bookDTOArrayList=new ArrayList<BookDTO>();
            if (!books.isEmpty()){
                for (Book book:books){
                    BookDTO bookDTO=this.convertEntityToDto(book);
                    bookDTOArrayList.add(bookDTO);
                }
            }
            else{
                throw new BookException("No books found");
            }
            return bookDTOArrayList;

        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Error occurred - Contact Developer",e);
        }
    }

    private Book convertDtoToEntity(BookDTO book){
        Book bookEntity=new Book();
        bookEntity.setId(book.getId());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setName(book.getName());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setPublisherId(book.getPublisherId());
        bookEntity.setMainCategoryId(book.getMainCategoryId());
        return bookEntity;
    }

    private BookDTO convertEntityToDto(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublisherId(book.getPublisherId());
        bookDTO.setMainCategoryId(book.getMainCategoryId());
        return bookDTO;
    }
}
