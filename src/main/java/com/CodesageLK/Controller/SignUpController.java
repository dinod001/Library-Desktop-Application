package com.CodesageLK.Controller;

import com.CodesageLK.dto.Impl.SignUpDto;
import com.CodesageLK.service.Custom.Impl.SignUpServiceImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpController {
    public TextField txtUsername;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;

    private SignUpServiceImpl signUpService=RepoServiceFactory.getInstance().getService(RepoServiceTypes.SIGNUP_SERVICE);
    String username, email, password, confirmPassword;

    public void btnSignUpOnAction(ActionEvent actionEvent) {
        if (saveUser()){
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
    public boolean validateInputs() {
        username = txtUsername.getText();
        email = txtEmail.getText();
        password = txtPassword.getText();
        confirmPassword = txtConfirmPassword.getText();

        String usernameRegex = "^[a-zA-Z0-9_]{5,15}$"; // 5-15 characters, letters, numbers, underscore
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Standard email pattern
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"; // Min 8 chars, 1 letter, 1 number, 1 special char

        if (txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return false;
        }

        if (!Pattern.matches(usernameRegex, username)) {
            new Alert(Alert.AlertType.ERROR, "Username must be 5-15 characters long and can contain letters, numbers, and underscores.").show();
            return false;
        }

        if (!Pattern.matches(emailRegex, email)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid email address (example@domain.com).").show();
            return false;
        }

        if (!Pattern.matches(passwordRegex, password)) {
            new Alert(Alert.AlertType.ERROR,"Password must be at least 8 characters long, with at least 1 letter, 1 number, and 1 special character.").show();
            return false;
        }

        if (!Objects.equals(password, confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Password and confirm password do not match.").show();
            return false;
        }
        if (signUpService.usernameExists(username)) {
            new Alert(Alert.AlertType.ERROR, "Username already exists.").show();
            return false;
        }

        return true;
    }

    public boolean saveUser(){
        if (validateInputs()) {
            SignUpDto signUpDto = new SignUpDto();
            signUpDto.setUserName(username);
            signUpDto.setPassword(password);
            signUpDto.setEmail(email);
            signUpDto.setPassword(confirmPassword);
            if (signUpService.save(signUpDto)) {
                new Alert(Alert.AlertType.INFORMATION, "Sign Up Successful").showAndWait();
                return true;
            }
            new Alert(Alert.AlertType.ERROR, "Sign Up Failed").show();
        }
        return false;
    }
}
