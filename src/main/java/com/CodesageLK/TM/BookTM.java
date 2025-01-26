package com.CodesageLK.TM;

import java.util.List;

public class BookTM {
    private int id;
    private String name;
    private float price;
    private String isbn;
    private int publisherId;
    private int mainCategoryId;
    private List<Integer> AuthorerIds;
    private List<Integer> subCategoryIds;

    public BookTM(int id, String name, float price, String isbn, int publisherId, int mainCategoryId, List<Integer> authorerIds, List<Integer> subCategoryIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isbn = isbn;
        this.publisherId = publisherId;
        this.mainCategoryId = mainCategoryId;
        AuthorerIds = authorerIds;
        this.subCategoryIds = subCategoryIds;
    }

    public BookTM(int id, String name, float price, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isbn = isbn;
    }

    public BookTM() {
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public List<Integer> getAuthorerIds() {
        return AuthorerIds;
    }

    public void setAuthorerIds(List<Integer> authorerIds) {
        AuthorerIds = authorerIds;
    }

    public List<Integer> getSubCategoryIds() {
        return subCategoryIds;
    }

    public void setSubCategoryIds(List<Integer> subCategoryIds) {
        this.subCategoryIds = subCategoryIds;
    }
}
