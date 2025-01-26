package com.CodesageLK.TM;

import javafx.scene.control.CheckBox;

public class AuthorTMWithCheckBox {
    private int id;
    private String name;
    private String contact;
    private CheckBox checkBox;

    public AuthorTMWithCheckBox(int id, String name, String contact, CheckBox checkBox) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.checkBox = checkBox;
    }

    public AuthorTMWithCheckBox() {
        this.checkBox = new CheckBox();
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
