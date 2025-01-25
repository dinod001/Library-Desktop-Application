package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.MemberTM;
import com.CodesageLK.dto.Impl.MemberDTO;
import com.CodesageLK.repo.Custom.Impl.MemberRepoImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import com.CodesageLK.utill.exception.Custom.MemberCustomException;
import com.CodesageLK.service.Custom.Impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageMembersController {
    public TextField txtMemberID;
    public TextField txtMembrName;
    public TextField txtMemberAdress;
    public TextField txtMemberEmail;
    public TextField txtMemberContact;
    public TableView<MemberTM> tblMember;
    public TableColumn<MemberTM,String> clmMemberID;
    public TableColumn<MemberTM,String> clmMemberName;
    public TableColumn<MemberTM,String> clmMemberAdress;
    public TableColumn<MemberTM,String> clmMemberEmail;
    public TableColumn<MemberTM,String>clmMemberContact;


    /*MemberRepoImpl memberRepo=RepoFactory.getInstance().getRepo(RepoTypes.MEMBER_REPO);
    private final MemberServiceImpl memberServiceImpl = new MemberServiceImpl(memberRepo);*/

    private final MemberServiceImpl memberServiceImpl = RepoServiceFactory.getInstance().getService(RepoServiceTypes.MEMBER_SERVICE);

    public void initialize() {
        visulalize();
        tableShow();
    }


    //save new member
    public void btnSaveMemberOnAction(ActionEvent actionEvent) {
        MemberDTO memberDto = this.collectData();
        String errorMessage="Unexpected Error - contact Developer";
        boolean isSaved= false;
        try {
            isSaved = memberServiceImpl.add(memberDto);
        } catch (MemberCustomException e) {
            errorMessage=e.getMessage();
        }
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Member Added Successfully").show();
            tableShow();
        }
        else{
            new Alert(Alert.AlertType.ERROR,errorMessage).show();
        }
    }

    //update new member details
    public void btnUpdateMemberOnAction(ActionEvent actionEvent) {
        MemberDTO memberDto = this.collectData();
        String errorMessage="Data is still same - Updated Failed";
        boolean isUpdated= false;
        try {
            isUpdated = memberServiceImpl.update(memberDto);
            if(!isUpdated){
                errorMessage="User not found";
            }
        } catch (MemberCustomException e) {
            errorMessage=e.getMessage();
        }
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Member Updated Successfully").show();
            tableShow();
        }
        else{
            new Alert(Alert.AlertType.ERROR,errorMessage).show();
        }
    }

    //delete members details
    public void btnDeleteMemberOnAction(ActionEvent actionEvent) {
        String id = txtMemberID.getText();
        boolean isDeleted=false;
        String errorMessage="User deleted cancelled";
        Alert alert=new Alert(Alert.AlertType.WARNING,"Do you want to delete this member?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES){
            try {
                isDeleted= memberServiceImpl.delete(id);
                if(!isDeleted){
                    errorMessage="User Not found";
                }
            } catch (MemberCustomException e) {
                errorMessage=e.getMessage();
            }
        }
        if(isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Member Deleted Successfully").show();
            tableShow();
        }
        else{
            new Alert(Alert.AlertType.ERROR,errorMessage).show();
        }
    }

    /// search members
    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtMemberID.getText();
        Optional<MemberDTO> memberDto= memberServiceImpl.get(id);
        if(memberDto.isPresent()){
            txtMemberID.setText(memberDto.get().getId());
            txtMemberAdress.setText(memberDto.get().getAddress());
            txtMemberEmail.setText(memberDto.get().getEmail());
            txtMemberContact.setText(memberDto.get().getContact());
            txtMembrName.setText(memberDto.get().getName());
        }
        else{
            new Alert(Alert.AlertType.ERROR,"User not found").show();
        }
    }

    //visulize table data
    public void visulalize(){
        clmMemberID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmMemberAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmMemberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmMemberContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    //set data to table columns
    public void tableShow(){
        try {
            List<MemberDTO> allMembers = memberServiceImpl.getAll();
            List<MemberTM> memberTMS=new ArrayList<>();
            for (MemberDTO memberDto : allMembers) {
                MemberTM memberTM=new MemberTM();
                memberTM.setId(memberDto.getId());
                memberTM.setName(memberDto.getName());
                memberTM.setAddress(memberDto.getAddress());
                memberTM.setEmail(memberDto.getEmail());
                memberTM.setContact(memberDto.getContact());
                memberTMS.add(memberTM);
            }
            ObservableList<MemberTM> memberTMS1 = FXCollections.observableArrayList(memberTMS);
            tblMember.setItems(memberTMS1);
        } catch (MemberCustomException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    //clear textFilds
    public void btnClearOnAction(ActionEvent actionEvent) {
        txtMemberID.clear();
        txtMembrName.clear();
        txtMemberAdress.clear();
        txtMemberEmail.clear();
        txtMemberContact.clear();
    }

    public MemberDTO collectData(){
        String memberID = txtMemberID.getText();
        String memberName = txtMembrName.getText();
        String memberAdress = txtMemberAdress.getText();
        String memberEmail = txtMemberEmail.getText();
        String memberContact = txtMemberContact.getText();
        MemberDTO memberDto = new MemberDTO(memberID,memberName,memberAdress,memberEmail,memberContact);
        return memberDto;
    }


}
