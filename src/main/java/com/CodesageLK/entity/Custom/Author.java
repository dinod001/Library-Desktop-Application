package com.CodesageLK.entity.Custom;

import com.CodesageLK.entity.SuperEntity;

public class Author implements SuperEntity {
    private int id;
    private String name;
    private String contact;

    public Author(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public Author() {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
