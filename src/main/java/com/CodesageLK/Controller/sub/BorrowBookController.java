package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.BorrowBookTM;
import com.CodesageLK.dto.Impl.BorrowBookDTO;
import com.CodesageLK.entity.Custom.Book;
import com.CodesageLK.entity.Custom.Member;
import com.CodesageLK.repo.Custom.Impl.BookRepoImpl;
import com.CodesageLK.repo.Custom.Impl.BorrowBookImpl;
import com.CodesageLK.repo.Custom.Impl.MemberRepoImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.utill.exception.SuperException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BorrowBookController {
    public TextField txtMemberId;
    public TextField txtMemberName;
    public TextField txtMemberAdress;
    public TextField txtMemberContact;
    public TextField txtMemberEmail;
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtBookIsbn;
    public DatePicker dteBorrow;
    public DatePicker dteReturn;
    public TableView<BorrowBookTM> tblMain;
    public TableColumn<BorrowBookTM,Integer>clmBookId;
    public TableColumn<BorrowBookTM,String>clmBookName;
    public TableColumn<BorrowBookTM,String>clmMemberName;
    public TableColumn<BorrowBookTM,LocalDate>clmBorrorwDate;
    public TableColumn<BorrowBookTM,LocalDate> clmReturnDate;

    MemberRepoImpl memberRepo= RepoFactory.getInstance().getRepo(RepoTypes.MEMBER_REPO);
    BookRepoImpl bookRepo=RepoFactory.getInstance().getRepo(RepoTypes.BOOK_REPO);
    BorrowBookImpl borrowBook=RepoFactory.getInstance().getRepo(RepoTypes.BORROW_BOOK_REPO);
    List<BorrowBookDTO> borrowBookDTOS=new ArrayList<>();


    public void initialize(){
        visualize();
        loadData();
    }

    public void loadData(){
        try {
            List<BorrowBookDTO> borrowBookList = borrowBook.getBorrowBookList();
            List<BorrowBookTM> borrowBookTMList=new ArrayList<>();
            for (BorrowBookDTO borrowBookDTO : borrowBookList) {
                BorrowBookTM borrowBookTM=new BorrowBookTM();
                borrowBookTM.setId(borrowBookDTO.getBook_ID());
                borrowBookTM.setBook_Name(borrowBookDTO.getBook_Name());
                borrowBookTM.setBorrowed_Date(borrowBookDTO.getBorrowed_Date());
                borrowBookTM.setReturn_Date(borrowBookDTO.getReturn_Date());
                borrowBookTM.setMember_Name(borrowBookDTO.getMember_Name());
                borrowBookTMList.add(borrowBookTM);
            }
            ObservableList<BorrowBookTM> borrowBookTMS = FXCollections.observableArrayList(borrowBookTMList);
            tblMain.setItems(borrowBookTMS);

        } catch (SuperException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }

    }

    public void visualize(){
        clmBookId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmBookName.setCellValueFactory(new PropertyValueFactory<>("book_Name"));
        clmBorrorwDate.setCellValueFactory(new PropertyValueFactory<>("borrowed_Date"));
        clmReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_Date"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<>("member_Name"));
    }
    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        try {
            BorrowBookDTO collectdata = collectdata();
            borrowBookDTOS.add(collectdata);
            if (borrowBookDTOS.contains(collectdata)){
                new Alert(Alert.AlertType.INFORMATION,"Added to the cart").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Not Added to the cart").show();
            }
        } catch (SuperException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
        if (borrowBookDTOS.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"No books found").show();
        }else{
            try {
                boolean result = borrowBook.addBorrowBook(borrowBookDTOS);
                if (result){
                    new Alert(Alert.AlertType.INFORMATION,"book borrowed Successfully").show();
                    borrowBookDTOS.clear();
                    loadData();
                }else{
                    new Alert(Alert.AlertType.ERROR,"book borrowed unsuccessfully").show();
                }
            } catch (SuperException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void txtSearchMemberOnAction(ActionEvent actionEvent) {
        try {
            Optional<Member> member = memberRepo.get(txtMemberId.getText());
            if (member.isPresent()) {
                txtMemberName.setText(member.get().getName());
                txtMemberAdress.setText(member.get().getAddress());
                txtMemberContact.setText(member.get().getContact());
                txtMemberEmail.setText(member.get().getEmail());
            }else{
                new Alert(Alert.AlertType.ERROR,"Member not found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtSearchBookOnAction(ActionEvent actionEvent) {
        try {
            Optional<Book> book = bookRepo.get(Integer.parseInt(txtBookId.getText()));
            if (book.isPresent()){
                txtBookName.setText(book.get().getName());
                txtBookIsbn.setText(book.get().getIsbn());
            }else{
                new Alert(Alert.AlertType.ERROR,"Book not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public BorrowBookDTO collectdata() throws SuperException {
        try {
            String memberId = txtMemberId.getText();
            String memberName = txtMemberName.getText();
            int BookId= Integer.parseInt(txtBookId.getText());
            String BookName = txtBookName.getText();
            LocalDate date_borrow = dteBorrow.getValue();
            LocalDate date_return = dteReturn.getValue();
            boolean isReturn = false;

            BorrowBookDTO borrowBookDTO = new BorrowBookDTO();
            borrowBookDTO.setBook_ID(BookId);
            borrowBookDTO.setBook_Name(BookName);
            borrowBookDTO.setBorrowed_Date(date_borrow);
            borrowBookDTO.setReturn_Date(date_return);
            borrowBookDTO.setMember_Id(memberId);
            borrowBookDTO.setReturned_Date(null);
            borrowBookDTO.setIs_returned(isReturn);
            borrowBookDTO.setMember_Name(memberName);

            if (date_borrow == null || date_return == null) {
                throw new SuperException("Please select both borrow and return dates.");
            }

            return borrowBookDTO;

        }catch (NullPointerException | NumberFormatException e){
            throw new SuperException("Please fill all the blanks");
        }
    }
}
