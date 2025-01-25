package com.CodesageLK.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public TextField txtUsername;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;

    public void btnSignUpOnAction(ActionEvent actionEvent) {
        Stage window = (Stage)txtUsername.getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Login form load failed-Contact Developer").show();
            e.printStackTrace();
        }
    }
}
