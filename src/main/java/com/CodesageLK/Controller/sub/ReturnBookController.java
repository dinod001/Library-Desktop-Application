package com.CodesageLK.Controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ReturnBookController {
    public TextField txtKeyword;
    public RadioButton rdoBookId;
    public ToggleGroup radioGroup;
    public RadioButton rdoMemberId;
    public RadioButton rdoMemberContactNo;
    public TableView tblReturnBook;
    public TableColumn clmRecordId;
    public TableColumn clmBookId;
    public TableColumn clmBookName;
    public TableColumn clmMemberId;
    public TableColumn clmMemberName;
    public TableColumn clmReturnDate;
    public Label lblLateDateCount;
    public Label lblFine;
    public Label lblBalance;

    public void txtKeyWordOnAction(ActionEvent actionEvent) {
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
    }
}
