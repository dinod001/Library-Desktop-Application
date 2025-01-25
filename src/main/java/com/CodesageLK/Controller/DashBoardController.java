package com.CodesageLK.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DashBoardController {

    public AnchorPane MainSubAnchorPain;

    //side bar
    public void btnSideManageBooksOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_books.fxml");
    }

    public void btnSideManageMembersOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_members.fxml");
    }
    public void btnSideManageAuthorsAndPublishersOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_Authors_and_Publishers.fxml");
    }

    public void btnSideBorrowBooksRecordsOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Borrow_Book.fxml");
    }

    public void btnSideReturnBooksRecordsOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Return_Book.fxml");
    }


    //main bar
    public void btnMainManageBooksOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_books.fxml");
    }

    public void btnMainBooksRecordsOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_members.fxml");
    }

    public void btnMainManageOthersOnAction(ActionEvent actionEvent) {
        this.loadWindow("/View/Sub/Manage_Authors_and_Publishers.fxml");
    }

    //loading window common method
    public void loadWindow(String path){
        MainSubAnchorPain.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            ObservableList<Node> children = MainSubAnchorPain.getChildren();
            children.add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Can't load the window-Contact Developer").show();
            e.printStackTrace();
        }
    }

}
