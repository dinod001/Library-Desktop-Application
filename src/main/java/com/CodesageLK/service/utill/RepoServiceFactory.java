package com.CodesageLK.service.utill;

import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.*;
import com.CodesageLK.service.Custom.Impl.*;
import com.CodesageLK.service.SuperService;

public class RepoServiceFactory {

    private RepoFactory repoFactory=RepoFactory.getInstance();
    private static final RepoServiceFactory instance = new RepoServiceFactory();

    private final MemberService memberService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final SignUpService signUpService;
    private final ReturnBookServiceImpl returnBookServiceImpl;

    private RepoServiceFactory() {
        memberService=new MemberServiceImpl(repoFactory.getRepo(RepoTypes.MEMBER_REPO));
        bookService=new BookServiceImpl(repoFactory.getRepo(RepoTypes.BOOK_REPO),
                repoFactory.getRepo(RepoTypes.BOOK_AUTHOR_REPO),repoFactory.getRepo(RepoTypes.SUBCATEGORY_REPO));
        authorService=new AuthorServiceImpl(repoFactory.getRepo(RepoTypes.AUTHOR_REPO));
        publisherService=new PublisherServiceImpl(repoFactory.getRepo(RepoTypes.PUBLISHER_REPO));
        categoryService=new CategorySeriveImpl(repoFactory.getRepo(RepoTypes.CATEGORY_REPO));
        signUpService=new SignUpServiceImpl(repoFactory.getRepo(RepoTypes.SIGNUP_REPO));
        returnBookServiceImpl=new ReturnBookServiceImpl();
    }
    public <T extends SuperService> T getService(RepoServiceTypes type) {
        switch (type) {
            case BOOK_SERVICE: return (T) bookService;
            case MEMBER_SERVICE: return (T) memberService;
            case AUTHOR_SERVICE: return (T) authorService;
            case PUBLISHER_SERVICE: return (T) publisherService;
            case CATEGORY_SERVICE: return (T) categoryService;
            case SIGNUP_SERVICE: return (T) signUpService;
            case RETURN_BOOK_SERVICE: return (T) returnBookServiceImpl;
            default: return null;
        }
    }
    public static RepoServiceFactory getInstance() {
        return instance;
    }
}
