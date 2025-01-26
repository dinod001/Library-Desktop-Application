package com.CodesageLK.entity.Custom;

import com.CodesageLK.entity.SuperEntity;

public class SubCategory implements SuperEntity {
    private int bookId;
    private int categoryId;

    public SubCategory(int bookId, int categoryId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public SubCategory() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
