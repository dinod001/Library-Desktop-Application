package com.CodesageLK.TM;

import java.time.LocalDate;

public class ReturnBookTM {
    private int id;
    private int bookId;
    private String bookName;
    private String memberId;
    private String memberName;
    private LocalDate returnDate;
    private LocalDate borrowDate;

    public ReturnBookTM(int id, int bookId, String bookName, String memberId, String memberName, LocalDate returnDate, LocalDate borrowDate) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.memberId = memberId;
        this.memberName = memberName;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
    }

    public ReturnBookTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
}
