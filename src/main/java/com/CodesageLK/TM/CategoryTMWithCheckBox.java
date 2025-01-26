package com.CodesageLK.TM;

import javafx.scene.control.CheckBox;

public class CategoryTMWithCheckBox {
    private int id;
    private String name;
    private CheckBox checkBox;

    public CategoryTMWithCheckBox(int id, String name, CheckBox checkBox) {
        this.id = id;
        this.name = name;
        this.checkBox = checkBox;
    }

    public CategoryTMWithCheckBox() {
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
