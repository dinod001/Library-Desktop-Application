package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.PublisherTM;
import com.CodesageLK.dto.Impl.PublisherDTO;
import com.CodesageLK.repo.Custom.Impl.AuthorImpl;
import com.CodesageLK.repo.Custom.Impl.PublisherImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.Impl.AuthorServiceImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import com.CodesageLK.utill.exception.Custom.PublisherException;
import com.CodesageLK.service.Custom.Impl.PublisherServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public TableView tblAuthor;
    public TableColumn clmAuthorId;
    public TableColumn clmAuthorName;
    public TableColumn clmAuthorContact;
    public TableColumn clmAUthorName;
    public TableColumn clmAUthorContact;


    private final AuthorServiceImpl authorService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.AUTHOR_SERVICE);
    public final PublisherServiceImpl publisherService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.PUBLISHER_SERVICE);

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

    }
    public void visualize(){
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    public void btnPublisherClearOnAction(ActionEvent actionEvent) {
        txtPublisherID.clear();
        txtPublisherName.clear();
        txtPublisherLocation.clear();
        txtPublisherContact.clear();
    }

    public void tbnAuthorSaveOnAction(ActionEvent actionEvent) {
    }

    public void tbnAuthorUpdateOnAction(ActionEvent actionEvent) {

    }

    public void tbnAuthorDeleteOnAction(ActionEvent actionEvent) {

    }

    public void tbnAuthorClearOnAction(ActionEvent actionEvent) {
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

    private void setData(PublisherDTO publisherDTO){
        txtPublisherID.setText(String.valueOf(publisherDTO.getId()));
        txtPublisherName.setText(publisherDTO.getName());
        txtPublisherLocation.setText(publisherDTO.getLocation());
        txtPublisherContact.setText(publisherDTO.getContact());
    }

}
