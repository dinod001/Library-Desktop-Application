package com.CodesageLK.entity.Custom;

import com.CodesageLK.entity.SuperEntity;

public class BookAuthor implements SuperEntity {
    private int bookID;
    private int authorID;

    public BookAuthor(int bookID, int authorID) {
        this.bookID = bookID;
        this.authorID = authorID;
    }

    public BookAuthor() {
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}
