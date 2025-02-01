package com.CodesageLK.Controller;

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

public class RenewController {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;

    private SignUpServiceImpl signUpService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.SIGNUP_SERVICE);

    public void btnRenewOnAction(ActionEvent actionEvent) {
        if (validation()){
            boolean updatedQuery = signUpService.update(txtUsername.getText(), txtPassword.getText());
            if (updatedQuery){
                new Alert(Alert.AlertType.INFORMATION,"Password updated successfully").show();
                Stage window = (Stage)txtUsername.getScene().getWindow();
                window.close();
                Stage stage = new Stage();
                try {
                    Parent load = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));
                    Scene scene = new Scene(load);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Login form load failed-Contact Developer").showAndWait();
                    e.printStackTrace();
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION,"updated Unsuccessfully,Username not found").show();
            }
        }

    }
    public boolean validation(){
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill al required fields!").show();
        }
        if (!Pattern.matches(passwordRegex, txtPassword.getText())) {
            new Alert(Alert.AlertType.ERROR,"Password must be at least 8 characters long, with at least 1 letter, 1 number, and 1 special character.").show();
            return false;
        }else{
            if (Objects.equals(txtConfirmPassword.getText(), txtConfirmPassword.getText())){
                return true;
            }else{
                new Alert(Alert.AlertType.ERROR,"Passwords do not match!").show();
            }
        }
        return false;
    }
}
