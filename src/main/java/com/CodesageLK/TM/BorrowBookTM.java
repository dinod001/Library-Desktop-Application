package com.CodesageLK.TM;

import java.time.LocalDate;

public class BorrowBookTM {
    private int Id;
    private LocalDate borrowed_Date;
    private LocalDate return_Date;
    private String book_Name;
    private String member_Name;


    public BorrowBookTM(int id, LocalDate borrowed_Date, LocalDate return_Date, String book_Name, String member_Name) {
        Id = id;
        this.borrowed_Date = borrowed_Date;
        this.return_Date = return_Date;
        this.book_Name = book_Name;
        this.member_Name = member_Name;
    }

    public BorrowBookTM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getBorrowed_Date() {
        return borrowed_Date;
    }

    public void setBorrowed_Date(LocalDate borrowed_Date) {
        this.borrowed_Date = borrowed_Date;
    }

    public LocalDate getReturn_Date() {
        return return_Date;
    }

    public void setReturn_Date(LocalDate return_Date) {
        this.return_Date = return_Date;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

    public String getMember_Name() {
        return member_Name;
    }

    public void setMember_Name(String member_Name) {
        this.member_Name = member_Name;
    }
}
