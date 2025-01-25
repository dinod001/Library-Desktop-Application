package com.CodesageLK.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginFormController {
    public TextField txtUsername;
    public Hyperlink lnkForogotPassword;
    public Button btnLogin;
    public Button btnCreateAccount;
    public PasswordField txtPassword;
    public AnchorPane subAnchorPain2;

    //login button
    public void btnLoginOnAction(ActionEvent actionEvent) {
        Stage window = (Stage)txtPassword.getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/View/Dashboard_form.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //login create account
    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        subAnchorPain2.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/View/signUp_form.fxml"));
            ObservableList<Node> children = subAnchorPain2.getChildren();
            children.add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Sign Up form load failed-Contact Developer").show();
            e.printStackTrace();
        }

    }
}
