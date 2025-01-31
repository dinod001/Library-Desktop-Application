package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.SignUpDto;
import com.CodesageLK.entity.Custom.SignUp;
import com.CodesageLK.repo.Custom.SignUpRepo;
import com.CodesageLK.service.Custom.SignUpService;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService  {
    private SignUpRepo signUpRepo;
    public SignUpServiceImpl(SignUpRepo repo) {
        this.signUpRepo = repo;
    }

    @Override
    public boolean save(SignUpDto signUpDto) {
        try {
            if (signUpRepo.save(convertDtoToEntity(signUpDto))) return true;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Error Occurred-contact Developer").show();
        }
        return false;
    }

    @Override
    public boolean update(SignUpDto signUpDto) {
        try {
            if (signUpRepo.update(convertDtoToEntity(signUpDto))) return true;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Error Occurred-contact Developer").show();
        }
        return false;
    }

    @Override
    public boolean search(String username,String password) {
        try {
            if (signUpRepo.search(username,password)) return true;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Error Occurred-contact Developer").show();
        }
        return false;
    }

    public SignUp convertDtoToEntity(SignUpDto signUpDto) {
        SignUp signUp = new SignUp();
        signUp.setUserName(signUpDto.getUserName());
        signUp.setPassword(signUpDto.getPassword());
        signUp.setEmail(signUpDto.getEmail());
        return signUp;
    }
}
