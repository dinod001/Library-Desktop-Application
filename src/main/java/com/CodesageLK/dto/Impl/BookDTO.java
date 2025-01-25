package com.CodesageLK.dto.Impl;

import com.CodesageLK.dto.SuperDTO;

public class BookDTO implements SuperDTO {
    private int id;
    private String name;
    private float price;
    private String isbn;
    private String publisherId;
    private String mainCategoryId;

    public BookDTO(int id, String name, float price, String isbn, String publisherId, String mainCategoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isbn = isbn;
        this.publisherId = publisherId;
        this.mainCategoryId = mainCategoryId;
    }

    public BookDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(String mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }
}
