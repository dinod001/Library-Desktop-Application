package com.CodesageLK.Controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BorrowBookController {
    public TextField txtMemberId;
    public TextField txtMemberName;
    public TextField txtMemberAdress;
    public TextField txtMemberContact;
    public TextField txtMemberEmail;
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtBookIsbn;
    public TextField txtBookMainCategory;
    public DatePicker dteBorrow;
    public DatePicker dteReturn;
    public TableView tblMain;
    public TableColumn clmBookId;
    public TableColumn clmBookName;
    public TableColumn clmMemberName;
    public TableColumn clmBorrorwDate;
    public TableColumn clmReturnDate;


    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {

    }
}
