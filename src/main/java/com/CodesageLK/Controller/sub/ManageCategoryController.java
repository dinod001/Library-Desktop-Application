package com.CodesageLK.Controller.sub;

import com.CodesageLK.TM.CategoryTM;
import com.CodesageLK.dto.Impl.CategoryDTO;
import com.CodesageLK.service.Custom.Impl.CategorySeriveImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import com.CodesageLK.utill.exception.Custom.CategoryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageCategoryController {
    public TextField txtCategoryId;
    public TextField txtCategoryName;
    public TableView<CategoryTM> tblCategory;
    public TableColumn<CategoryTM,Integer> clmCategoryId;
    public TableColumn<CategoryTM,String> clmCategoryName;

    CategorySeriveImpl categorySerive= RepoServiceFactory.getInstance().getService(RepoServiceTypes.CATEGORY_SERVICE);
    private ManageBooksController booksController;

    public void setManageCategoryController(ManageBooksController booksController) {
        this.booksController=booksController;
    }

    public void initialize() {
        visualize();
        loadData();
    }

    private void loadData() {
        try {
            List<CategoryDTO> all = categorySerive.getAll();
            List<CategoryTM> categoryTMs = new ArrayList<>();
            if (all != null) {
                for (CategoryDTO categoryDTO : all) {
                    CategoryTM categoryTM = new CategoryTM();
                    categoryTM.setId(categoryDTO.getId());
                    categoryTM.setName(categoryDTO.getName());
                    categoryTMs.add(categoryTM);
                }
            }
            ObservableList<CategoryTM> categoryTMS = FXCollections.observableArrayList(categoryTMs);
            tblCategory.setItems(categoryTMS);
            if(booksController!=null)booksController.loadCategoryData();
        } catch (CategoryException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void visualize() {
        clmCategoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    public void btnCategorySaveOnAction(ActionEvent actionEvent) {
        try {
            boolean result=categorySerive.add(collectData());
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category added successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Category added Unsuccessfully-Something went wrong").show();
            }
        } catch (CategoryException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void btnCategoryUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean result=categorySerive.update(collectData());
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category updated successfully").show();
                loadData();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Category update Unsuccessfully-Something went wrong").show();
            }
        } catch (CategoryException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnCategoryDeleteOnAction(ActionEvent actionEvent) {
        if (collectData().getId()==0){
            new Alert(Alert.AlertType.ERROR,"Please Add valid ID").show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete this category?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                try {
                    boolean result=categorySerive.delete(collectData().getId());
                    if(result){
                        new Alert(Alert.AlertType.INFORMATION,"Category deleted successfully").show();
                        loadData();
                    }
                    else{
                        new Alert(Alert.AlertType.ERROR,"Category deleted Unsuccessfully-Something went wrong").show();
                    }
                } catch (CategoryException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }

        }
    }

    public void btnCategoryClearOnAction(ActionEvent actionEvent) {
        txtCategoryId.clear();
        txtCategoryName.clear();
    }

    public void txtSearchCategoryIdOnAction(ActionEvent actionEvent) {
        try {
            Optional<CategoryDTO> categoryDTO = categorySerive.get(collectData().getId());
            if(categoryDTO.isPresent()){
                txtCategoryId.setText(String.valueOf(categoryDTO.get().getId()));
                txtCategoryName.setText(String.valueOf(categoryDTO.get().getName()));
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Category not found").show();
            }
        } catch (CategoryException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private CategoryDTO collectData(){
        String categoryId = txtCategoryId.getText();
        String categoryName = txtCategoryName.getText();
        CategoryDTO categoryDTO = new CategoryDTO();
        int id=0;
        try{
            id= Integer.parseInt(categoryId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        categoryDTO.setId(id);
        categoryDTO.setName(categoryName);
        return categoryDTO;
    }
}
