package com.CodesageLK.service.utill;

import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.AuthorService;
import com.CodesageLK.service.Custom.BookService;
import com.CodesageLK.service.Custom.Impl.AuthorServiceImpl;
import com.CodesageLK.service.Custom.Impl.BookServiceImpl;
import com.CodesageLK.service.Custom.Impl.MemberServiceImpl;
import com.CodesageLK.service.Custom.Impl.PublisherServiceImpl;
import com.CodesageLK.service.Custom.MemberService;
import com.CodesageLK.service.Custom.PublisherService;
import com.CodesageLK.service.SuperService;

public class RepoServiceFactory {

    private RepoFactory repoFactory=RepoFactory.getInstance();
    private static final RepoServiceFactory instance = new RepoServiceFactory();

    private final MemberService memberService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    private RepoServiceFactory() {
        memberService=new MemberServiceImpl(repoFactory.getRepo(RepoTypes.MEMBER_REPO));
        bookService=new BookServiceImpl(repoFactory.getRepo(RepoTypes.BOOK_REPO));
        authorService=new AuthorServiceImpl(repoFactory.getRepo(RepoTypes.AUTHOR_REPO));
        publisherService=new PublisherServiceImpl(repoFactory.getRepo(RepoTypes.PUBLISHER_REPO));
    }
    public <T extends SuperService> T getService(RepoServiceTypes type) {
        switch (type) {
            case BOOK_SERVICE: return (T) bookService;
            case MEMBER_SERVICE: return (T) memberService;
            case AUTHOR_SERVICE: return (T) authorService;
            case PUBLISHER_SERVICE: return (T) publisherService;
            default: return null;
        }
    }
    public static RepoServiceFactory getInstance() {
        return instance;
    }
}
