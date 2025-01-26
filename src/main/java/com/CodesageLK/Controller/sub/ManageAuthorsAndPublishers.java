package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.AuthorTM;
import com.CodesageLK.TM.PublisherTM;
import com.CodesageLK.dto.Impl.AuthorDTO;
import com.CodesageLK.dto.Impl.PublisherDTO;
import com.CodesageLK.repo.Custom.Impl.AuthorImpl;
import com.CodesageLK.repo.Custom.Impl.PublisherImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.Impl.AuthorServiceImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import com.CodesageLK.utill.exception.Custom.AuthorException;
import com.CodesageLK.utill.exception.Custom.PublisherException;
import com.CodesageLK.service.Custom.Impl.PublisherServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageAuthorsAndPublishers {
    public TextField txtPublisherID;
    public TextField txtPublisherName;
    public TextField txtPublisherLocation;
    public TextField txtPublisherContact;
    public TableView<PublisherTM> tblPublisher;
    public TableColumn<PublisherTM,Integer> clmId;
    public TableColumn<PublisherTM,String> clmName;
    public TableColumn<PublisherTM,String> clmContact;
    public TextField txtAuthorID;
    public TextField txtAuthorName;
    public TextField txtAuthorContact;
    public TableView<AuthorTM>tblAuthor;
    public TableColumn<AuthorTM,Integer>clmAuthorId;
    public TableColumn<AuthorTM,String>clmAUthorContact;


    private final AuthorServiceImpl authorService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.AUTHOR_SERVICE);
    public final PublisherServiceImpl publisherService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.PUBLISHER_SERVICE);
    public TableColumn<AuthorTM,String> clmAUthorName;

    public void initialize() {
        loadData();
        visualize();
    }

    public void btnPublisherSaveOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = this.collectPublisherData();
        try {
            boolean result=publisherService.add(publisherDTO);
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Added Successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Added Unsuccessfully - Something went wrong ").show();
            }
        } catch (PublisherException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnPublisherUpdateOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = this.collectPublisherData();
        try {
            boolean result=publisherService.update(publisherDTO);
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Update Unsuccessfully - Something went wrong ").show();
            }
        } catch (PublisherException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnPublisherDeleteOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = this.collectPublisherData();
        if (publisherDTO.getId()==0){
            new Alert(Alert.AlertType.INFORMATION,"Please enter valid ID").show();
            return;
        }
        try {
            Alert alert= new Alert(Alert.AlertType.WARNING,"Do you want to delete this publisher?", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                boolean result=publisherService.delete(publisherDTO.getId());
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
                    loadData();
                }
                else{
                    new Alert(Alert.AlertType.ERROR,"Deleted Unsuccessfully - Something went wrong ").show();
                }
            }
        } catch (PublisherException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtSearchPublisherOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = this.collectPublisherData();
        try {
            Optional<PublisherDTO> publisherDTO1 = publisherService.get(publisherDTO.getId());
            if (publisherDTO1.isPresent()) {
                setData(publisherDTO1.get());
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Publisher Not Found").show();
            }

        } catch (PublisherException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void loadData(){
        //publisher
        try {
            List<PublisherDTO> all = publisherService.getAll();
            List<PublisherTM> publisherTMList=new ArrayList<>();
            for (PublisherDTO publisherDTO : all) {
                PublisherTM publisherTM=new PublisherTM();
                publisherTM.setId(publisherDTO.getId());
                publisherTM.setName(publisherDTO.getName());
                publisherTM.setContact(publisherDTO.getContact());
                publisherTMList.add(publisherTM);
            }
            ObservableList<PublisherTM> publisherTMS = FXCollections.observableArrayList(publisherTMList);
            tblPublisher.setItems(publisherTMS);
        } catch (PublisherException e) {
            throw new RuntimeException(e);
        }

        //Author
        try {
            List<AuthorDTO> all = authorService.getAll();
            List<AuthorTM> authorTMList=new ArrayList<>();
            for (AuthorDTO authorDTO : all) {
                AuthorTM authorTM=new AuthorTM();
                authorTM.setId(authorDTO.getId());
                authorTM.setName(authorDTO.getName());
                authorTM.setContact(authorDTO.getContact());
                authorTMList.add(authorTM);
            }
            ObservableList<AuthorTM> authorTMS = FXCollections.observableArrayList(authorTMList);
            tblAuthor.setItems(authorTMS);
        } catch (AuthorException e) {
            throw new RuntimeException(e);
        }

    }
    public void visualize(){
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        clmAuthorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmAUthorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAUthorContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    public void btnPublisherClearOnAction(ActionEvent actionEvent) {
        txtPublisherID.clear();
        txtPublisherName.clear();
        txtPublisherLocation.clear();
        txtPublisherContact.clear();
    }

    public void tbnAuthorSaveOnAction(ActionEvent actionEvent) {
        try {
            boolean result=authorService.add(this.collectAuthorData());
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Added Successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Add Unsuccessfully - Something went wrong ").show();
            }
        } catch (AuthorException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void tbnAuthorUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean result=authorService.update(this.collectAuthorData());
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Updated Successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Updated failed").show();
            }
        } catch (AuthorException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void tbnAuthorDeleteOnAction(ActionEvent actionEvent) {
        if (this.collectAuthorData().getId()==0){
            new Alert(Alert.AlertType.INFORMATION,"Please enter valid ID").show();
            return;
        }
        Alert alert= new Alert(Alert.AlertType.WARNING,"Do you want to delete this publisher?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                boolean result=authorService.delete(this.collectAuthorData().getId());
                if(result){
                    new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully").show();
                    loadData();
                }
                else{
                    new Alert(Alert.AlertType.ERROR,"Deleted failed").show();
                }
            } catch (AuthorException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public void txtAuthorSearch(ActionEvent actionEvent) {
        try {
            Optional<AuthorDTO> authorDTO = authorService.get(this.collectAuthorData().getId());
            if (authorDTO.isPresent()) {
                txtAuthorName.setText(authorDTO.get().getName());
                txtAuthorContact.setText(authorDTO.get().getContact());
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Author Not Found").show();
            }
        } catch (AuthorException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void tbnAuthorClearOnAction(ActionEvent actionEvent) {
        txtAuthorID.clear();
        txtAuthorName.clear();
        txtAuthorContact.clear();
    }

    private PublisherDTO collectPublisherData(){
        String publisherID = txtPublisherID.getText();
        String publisherName = txtPublisherName.getText();
        String publisherLocation = txtPublisherLocation.getText();
        String publisherContact = txtPublisherContact.getText();

        int id=0;
        try {
            id = Integer.parseInt(publisherID);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(id);
        publisherDTO.setName(publisherName);
        publisherDTO.setLocation(publisherLocation);
        publisherDTO.setContact(publisherContact);
        return publisherDTO;
    }

    private AuthorDTO collectAuthorData(){
        String authorID = txtAuthorID.getText();
        String authorName = txtAuthorName.getText();
        String authorContact = txtAuthorContact.getText();
         int id=0;
         try {
             id = Integer.parseInt(authorID);
         }catch (NumberFormatException e){
             e.printStackTrace();
         }
         AuthorDTO authorDTO = new AuthorDTO();
         authorDTO.setId(id);
         authorDTO.setName(authorName);
         authorDTO.setContact(authorContact);
         return authorDTO;
    }

    private void setData(PublisherDTO publisherDTO){
        txtPublisherID.setText(String.valueOf(publisherDTO.getId()));
        txtPublisherName.setText(publisherDTO.getName());
        txtPublisherLocation.setText(publisherDTO.getLocation());
        txtPublisherContact.setText(publisherDTO.getContact());
    }


    public void rowSelectOnMouseClicked(MouseEvent mouseEvent) {
        AuthorTM selectedItems = tblAuthor.getSelectionModel().getSelectedItem();
        if (selectedItems!=null){
            txtAuthorID.setText(String.valueOf(selectedItems.getId()));
            txtAuthorSearch(null);
        }
    }
}
