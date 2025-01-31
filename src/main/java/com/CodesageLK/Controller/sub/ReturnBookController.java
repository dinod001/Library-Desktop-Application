package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.ReturnBookTM;
import com.CodesageLK.dto.Impl.BorrowBookDTO;
import com.CodesageLK.repo.Custom.Impl.BorrowBookImpl;
import com.CodesageLK.repo.Custom.Impl.ReturnBookImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.utill.exception.SuperException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookController {
    public TextField txtKeyword;
    public RadioButton rdoBookId;
    public ToggleGroup radioGroup;
    public RadioButton rdoMemberId;
    public RadioButton rdoMemberContactNo;
    public TableView<ReturnBookTM> tblReturnBook;
    public TableColumn<ReturnBookTM,Integer> clmRecordId;
    public TableColumn<ReturnBookTM,Integer> clmBookId;
    public TableColumn<ReturnBookTM,String> clmBookName;
    public TableColumn<ReturnBookTM,String> clmMemberId;
    public TableColumn<ReturnBookTM,String> clmMemberName;
    public TableColumn<ReturnBookTM, LocalDate> clmReturnDate;
    public Label lblLateDateCount;
    public Label lblFine;
    public Label lblBalance;
    public TextField txtFine;
    public TextField txtPaymnet;


    BorrowBookImpl borrowBook= RepoFactory.getInstance().getRepo(RepoTypes.BORROW_BOOK_REPO);
    ReturnBookImpl returnBook=RepoFactory.getInstance().getRepo(RepoTypes.RETURN_BOOK_REPO);

    public void initialize() {
        visualize();
        loadTable();

    }
    public void loadTable() {
        try {
            List<BorrowBookDTO> borrowBookList = borrowBook.getBorrowBookList();
            List<ReturnBookTM> returnBookTMS=new ArrayList<>();
            if (borrowBookList != null) {
                for (BorrowBookDTO borrowBookDTO : borrowBookList) {
                    returnBookTMS.add(dtoToTm(borrowBookDTO));
                }
                ObservableList<ReturnBookTM> returnBookTMS1 = FXCollections.observableArrayList(returnBookTMS);
                tblReturnBook.setItems(returnBookTMS1);
            }


        } catch (SuperException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void visualize(){
        clmRecordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        clmBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        clmReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    public void txtKeyWordOnAction(ActionEvent actionEvent) {
        if (!rdoBookId.isSelected() && !rdoMemberId.isSelected() && !rdoMemberContactNo.isSelected()) {
            new Alert(Alert.AlertType.INFORMATION, "Please select one of the options").show();
            return;
        }

        try {
            ArrayList<BorrowBookDTO> borrowBookDTO = searchBorrowBook();
            if (borrowBookDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Record not found").show();
            }
            tblReturnBook.getItems().clear();
            ObservableList<ReturnBookTM> borrowBookDTOS = FXCollections.observableArrayList(borrowBookDTO.stream().map(this::dtoToTm).toList());
            tblReturnBook.setItems(borrowBookDTOS);
        } catch (SuperException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private ArrayList<BorrowBookDTO> searchBorrowBook() throws SuperException {
        String keyword = txtKeyword.getText();

        if (rdoBookId.isSelected()) {
            return returnBook.searchByBookID(Integer.parseInt(keyword));
        } else if (rdoMemberId.isSelected()) {
            return returnBook.searchByMemberID(keyword);
        } else if (rdoMemberContactNo.isSelected()) {
            return returnBook.searchByMemberContactNo(keyword);
        }

        return null;
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
    }

    public void btnViewAllOnAction(ActionEvent actionEvent) {
        visualize();
        loadTable();
    }

    int dateCount=0;
    public void tblMousOnClickedListener(MouseEvent mouseEvent) {
        ReturnBookTM selectedItem = tblReturnBook.getSelectionModel().getSelectedItem();
        lblLateDateCount.setText("");
        if (selectedItem.getReturnDate().isBefore(LocalDate.now())) {
            dateCount= (int)ChronoUnit.DAYS.between(selectedItem.getReturnDate(), LocalDate.now());
            lblLateDateCount.setText(String.valueOf(dateCount));
        }
    }
    int fine=0;
    public void txtFineOnAaction(ActionEvent actionEvent) {
        try{
           fine=Integer.parseInt(txtFine.getText())*dateCount;
            lblFine.setText(String.valueOf(fine));
        } catch (NumberFormatException e) {
        }
    }

    public void txtPaymentOnAction(ActionEvent actionEvent) {
        int payment=Integer.parseInt(txtPaymnet.getText());
        try{
            if (payment!=fine){
                new Alert(Alert.AlertType.INFORMATION, "Insufficient Money").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Payment Successful").show();
            }
        }catch (NumberFormatException e) {

        }
    }

    public ReturnBookTM dtoToTm(BorrowBookDTO borrowBookDTO){
        ReturnBookTM returnBookTM=new ReturnBookTM();
        returnBookTM.setId(borrowBookDTO.getId());
        returnBookTM.setBookId(borrowBookDTO.getBook_ID());
        returnBookTM.setBookName(borrowBookDTO.getBook_Name());
        returnBookTM.setMemberId(borrowBookDTO.getMember_Id());
        returnBookTM.setMemberName(borrowBookDTO.getMember_Name());
        returnBookTM.setReturnDate(borrowBookDTO.getReturn_Date());
        returnBookTM.setBorrowDate(borrowBookDTO.getBorrowed_Date());
        return returnBookTM;
    }
}
