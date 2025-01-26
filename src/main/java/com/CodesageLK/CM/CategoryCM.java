package com.CodesageLK.CM;

public class CategoryCM {
    private int id;
    private String name;

    public CategoryCM(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryCM() {
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
}
