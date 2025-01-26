package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.BookDTO;
import com.CodesageLK.entity.Custom.Book;
import com.CodesageLK.entity.Custom.BookAuthor;
import com.CodesageLK.entity.Custom.SubCategory;
import com.CodesageLK.repo.Custom.BookAuthorRepo;
import com.CodesageLK.repo.Custom.Impl.BookAuthorImpl;
import com.CodesageLK.repo.Custom.SubCategoriesRepo;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.utill.DBConnection;
import com.CodesageLK.utill.exception.Custom.BookException;
import com.CodesageLK.utill.exception.Custom.MemberCustomException;
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
    private final BookAuthorRepo authorRepo;
    private final SubCategoriesRepo subCategoriesRepo;
    private BookAuthorImpl bookAuthor= RepoFactory.getInstance().getRepo(RepoTypes.BOOK_AUTHOR_REPO);
    public BookServiceImpl(BookRepoImpl bookRepo, BookAuthorRepo authorRepo, SubCategoriesRepo subCategoriesRepo) {
        this.repo = bookRepo;
        this.authorRepo = authorRepo;
        this.subCategoriesRepo = subCategoriesRepo;
    }
    @Override
    public boolean add(BookDTO bookDTO) throws SuperException {
        Book book=this.convertDtoToEntity(bookDTO);
        try {
            //autocommit set to false, since we need to store data once the all table successfully get their data
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            //save details in book , and setting book ID
            repo.add(book);
            List<BookAuthor> bookAuthors = bookDTO.getAuthorerIds().stream().map(e -> new BookAuthor(book.getId(), e)).toList();
            List<SubCategory> subCategories = bookDTO.getSubCategoryIds().stream().map(e -> new SubCategory(book.getId(), e)).toList();

            boolean flag=authorRepo.saveList(bookAuthors);
            if (flag) {
                flag=subCategoriesRepo.saveList(subCategories);
                System.out.println(flag);
                if (flag) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }else{
                    DBConnection.getInstance().getConnection().rollback();
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
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
        finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean update(BookDTO bookDTO) throws SuperException {
        Book  book=this.convertDtoToEntity(bookDTO);
        List<BookAuthor> bookAuthors = bookDTO.getAuthorerIds().stream().map(e -> new BookAuthor(bookDTO.getId(), e)).toList();
        List<SubCategory> subCategories = bookDTO.getSubCategoryIds().stream().map(e -> new SubCategory(bookDTO.getId(), e)).toList();
        //update students details
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            //update book table data
            boolean update = repo.update(book);
            if (update){
                //delete authors of that book from table data
                boolean IsBookAuthordeleted = bookAuthor.delete(bookDTO.getId());
                if (IsBookAuthordeleted){
                    //delete categories of that book
                    boolean isSubCategoriesDeleted = subCategoriesRepo.delete(bookDTO.getId());
                    if (isSubCategoriesDeleted){
                        //saving new data
                        boolean isBookAuthorSaved = bookAuthor.saveList(bookAuthors);
                        if (isBookAuthorSaved){
                            //saving new data
                            boolean isSubCategoriesSaved = subCategoriesRepo.saveList(subCategories);
                            if (isSubCategoriesSaved){
                                DBConnection.getInstance().getConnection().commit();
                                return true;
                            }
                        }
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new SuperException("Duplicate ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new SuperException("input data is too log in "+errors[1],e);
                }
            }
            throw new SuperException("Error occurred - Contact Developer",e);
        }finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
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
        bookEntity.setPublisherId(String.valueOf(book.getPublisherId()));
        bookEntity.setMainCategoryId(String.valueOf(book.getMainCategoryId()));
        return bookEntity;
    }

    private BookDTO convertEntityToDto(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublisherId(Integer.parseInt(book.getPublisherId()));
        bookDTO.setMainCategoryId(Integer.parseInt(book.getMainCategoryId()));
        return bookDTO;
    }
}
