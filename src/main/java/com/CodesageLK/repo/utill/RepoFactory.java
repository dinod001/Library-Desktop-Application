package com.CodesageLK.repo.utill;

import com.CodesageLK.repo.Custom.*;
import com.CodesageLK.repo.Custom.Impl.*;
import com.CodesageLK.service.Custom.Impl.ReturnBookServiceImpl;

public class RepoFactory {

    private static final RepoFactory instance = new RepoFactory();

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;
    private final PublisherRepo publisherRepo;
    private final CategoryRepo categoryRepo;
    private final BookAuthorRepo bookAuthorRepo;
    private final SubCategoriesRepo subCategoriesRepo;
    private final BorrowBookRepo borrowBookRepo;
    private final SignUpImpl signUpImpl;

    private RepoFactory() {
        this.authorRepo=new AuthorImpl();
        this.bookRepo=new BookRepoImpl();
        this.memberRepo=new MemberRepoImpl();
        this.publisherRepo=new PublisherImpl();
        this.categoryRepo=new CategoryImpl();
        this.bookAuthorRepo=new BookAuthorImpl();
        this.subCategoriesRepo=new SubCategoriesImpl();
        this.borrowBookRepo=new BorrowBookImpl();
        this.signUpImpl=new SignUpImpl();

    }
    public static RepoFactory getInstance() {
        return instance;
    }

    public <T> T getRepo(RepoTypes type) {
        switch (type) {
            case BOOK_REPO: return (T) bookRepo;
            case AUTHOR_REPO: return (T) authorRepo;
            case MEMBER_REPO: return (T) memberRepo;
            case PUBLISHER_REPO: return (T) publisherRepo;
            case CATEGORY_REPO: return (T) categoryRepo;
            case BOOK_AUTHOR_REPO: return (T) bookAuthorRepo;
            case SUBCATEGORY_REPO: return (T) subCategoriesRepo;
            case BORROW_BOOK_REPO: return (T) borrowBookRepo;
            case SIGNUP_REPO: return (T) signUpImpl;
            default: return null;
        }
    }
}
