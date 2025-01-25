package com.CodesageLK.dto.Impl;

import com.CodesageLK.dto.SuperDTO;

public class AuthorDTO implements SuperDTO {
    private int id;
    private String name;
    private String contact;

    public AuthorDTO(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public AuthorDTO() {
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
