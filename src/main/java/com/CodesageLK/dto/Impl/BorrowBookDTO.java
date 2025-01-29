package com.CodesageLK.dto.Impl;

import com.CodesageLK.dto.SuperDTO;

import java.time.LocalDate;

public class BorrowBookDTO implements SuperDTO {
    private int id;
    private LocalDate borrowed_Date;
    private boolean is_returned;
    private LocalDate return_Date;
    private LocalDate returned_Date;
    private int book_ID;
    private String member_Id;
    private String member_Name;
    private String Book_Name;

    public BorrowBookDTO(int id,LocalDate borrowed_Date, boolean is_returned, LocalDate return_Date, LocalDate returned_Date, int book_ID, String member_Id, String member_Name, String book_Name) {
        this.borrowed_Date = borrowed_Date;
        this.is_returned = is_returned;
        this.return_Date = return_Date;
        this.returned_Date = returned_Date;
        this.book_ID = book_ID;
        this.member_Id = member_Id;
        this.member_Name = member_Name;
        this.Book_Name = book_Name;
        this.id = id;
    }

    public BorrowBookDTO(int book_ID, String book_Name, String member_Name, LocalDate borrowed_Date, LocalDate return_Date) {
        this.book_ID = book_ID;
        Book_Name = book_Name;
        this.member_Name = member_Name;
        this.borrowed_Date = borrowed_Date;
        this.return_Date = return_Date;
    }

    public BorrowBookDTO() {
    }

    public LocalDate getBorrowed_Date() {
        return borrowed_Date;
    }

    public void setBorrowed_Date(LocalDate borrowed_Date) {
        this.borrowed_Date = borrowed_Date;
    }

    public boolean isIs_returned() {
        return is_returned;
    }

    public void setIs_returned(boolean is_returned) {
        this.is_returned = is_returned;
    }

    public LocalDate getReturn_Date() {
        return return_Date;
    }

    public void setReturn_Date(LocalDate return_Date) {
        this.return_Date = return_Date;
    }

    public LocalDate getReturned_Date() {
        return returned_Date;
    }

    public void setReturned_Date(LocalDate returned_Date) {
        this.returned_Date = returned_Date;
    }

    public int getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(int book_ID) {
        this.book_ID = book_ID;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    public String getMember_Name() {
        return member_Name;
    }

    public void setMember_Name(String member_Name) {
        this.member_Name = member_Name;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
