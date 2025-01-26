package com.CodesageLK.Controller.sub;

import com.CodesageLK.CM.CategoryCM;
import com.CodesageLK.CM.PublisherCM;
import com.CodesageLK.TM.BookTM;
import com.CodesageLK.TM.CategoryTMWithCheckBox;
import com.CodesageLK.TM.AuthorTMWithCheckBox;
import com.CodesageLK.dto.Impl.AuthorDTO;
import com.CodesageLK.dto.Impl.BookDTO;
import com.CodesageLK.dto.Impl.CategoryDTO;
import com.CodesageLK.dto.Impl.PublisherDTO;
import com.CodesageLK.entity.Custom.BookAuthor;
import com.CodesageLK.entity.Custom.SubCategory;
import com.CodesageLK.repo.Custom.Impl.BookAuthorImpl;
import com.CodesageLK.repo.Custom.Impl.SubCategoriesImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.Impl.AuthorServiceImpl;
import com.CodesageLK.service.Custom.Impl.BookServiceImpl;
import com.CodesageLK.service.Custom.Impl.CategorySeriveImpl;
import com.CodesageLK.service.Custom.Impl.PublisherServiceImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import com.CodesageLK.utill.DBConnection;
import com.CodesageLK.utill.exception.Custom.AuthorException;
import com.CodesageLK.utill.exception.Custom.CategoryException;
import com.CodesageLK.utill.exception.Custom.PublisherException;
import com.CodesageLK.utill.exception.SuperException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageBooksController {
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtBookIsbn;
    public TextField txtBookPrice;
    public ComboBox<PublisherCM> cmdPublisher;
    public ComboBox<CategoryCM> cmdMainCategory;
    public TableView<CategoryTMWithCheckBox> tblCategory;
    public TableColumn<CategoryTMWithCheckBox,String> clmCategoryName;
    public TableColumn<CategoryTMWithCheckBox, CheckBox> clmOption;
    public TableView<AuthorTMWithCheckBox> tblAuthor;
    public TableColumn<AuthorTMWithCheckBox,String>clmAuthorName;
    public TableColumn<AuthorTMWithCheckBox,CheckBox> clmAuthorOption;
    public TableView<BookTM> tblBook;
    public TableColumn<BookTM,Integer> clmID;
    public TableColumn<BookTM,String> clmName;
    public TableColumn<BookTM,String> clmIsbn;
    public TableColumn<BookTM,Float> clmPrice;

   /* BookRepoImpl bookRepo= RepoFactory.getInstance().getRepo(RepoTypes.BOOK_REPO);
    BookServiceImpl bookService=new BookServiceImpl(bookRepo);*/

    private BookServiceImpl bookService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.BOOK_SERVICE);
    private PublisherServiceImpl publisherService= RepoServiceFactory.getInstance().getService(RepoServiceTypes.PUBLISHER_SERVICE);
    private CategorySeriveImpl categorySerive=RepoServiceFactory.getInstance().getService(RepoServiceTypes.CATEGORY_SERVICE);
    private AuthorServiceImpl authorService=RepoServiceFactory.getInstance().getService(RepoServiceTypes.AUTHOR_SERVICE);


    public void initialize() {
        this.setDataToComboBox();
        visualize();
        loadPublihserData();
        loadCategoryData();
        loadTable();
    }

    public void loadPublihserData(){
        //publisher
        try {
            List<PublisherDTO> all = publisherService.getAll();
            List<PublisherCM> publisherCMS=new ArrayList<>();
            for (PublisherDTO publisherDTO : all) {
                PublisherCM publisherCM=new PublisherCM();
                publisherCM.setId(publisherDTO.getId());
                publisherCM.setName(publisherDTO.getName());
                publisherCMS.add(publisherCM);
            }
            cmdPublisher.setItems(FXCollections.observableList(publisherCMS));
        } catch (PublisherException e) {
            throw new RuntimeException(e);
        }

        //publisher with check box
        try {
            List<AuthorDTO> all = authorService.getAll();
            List<AuthorTMWithCheckBox> authorTMWithCheckBoxes =new ArrayList<>();
            for (AuthorDTO authorDTO : all) {
                AuthorTMWithCheckBox authorTMWithCheckBox =new AuthorTMWithCheckBox();
                authorTMWithCheckBox.setId(authorDTO.getId());
                authorTMWithCheckBox.setName(authorDTO.getName());
                authorTMWithCheckBox.setCheckBox(new CheckBox());
                authorTMWithCheckBoxes.add(authorTMWithCheckBox);
            }
            tblAuthor.setItems(FXCollections.observableList(authorTMWithCheckBoxes));
        } catch (AuthorException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable(){
        try {
            List<BookDTO> all = bookService.getAll();
            List<BookTM> bookTMS=all.stream().map(e->new BookTM(e.getId(), e.getName(),e.getPrice(), e.getIsbn())).toList();
            ObservableList<BookTM> bookTMS1 = FXCollections.observableList(bookTMS);
            tblBook.setItems(bookTMS1);
        } catch (SuperException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadCategoryData(){
        //category
        try {
            List<CategoryDTO> all = categorySerive.getAll();
            List<CategoryCM> categoryCMS=new ArrayList<>();
            for (CategoryDTO categoryDTO : all) {
                CategoryCM categoryCM=new CategoryCM();
                categoryCM.setId(categoryDTO.getId());
                categoryCM.setName(categoryDTO.getName());
                categoryCMS.add(categoryCM);
            }
            cmdMainCategory.setItems(FXCollections.observableList(categoryCMS));

        } catch (CategoryException e) {
            throw new RuntimeException(e);
        }

        //category with check box
        try {
            List<CategoryDTO> all = categorySerive.getAll();
            List<CategoryTMWithCheckBox> categoryTMWithCheckBoxes=new ArrayList<>();
            for (CategoryDTO categoryDTO : all) {
                CategoryTMWithCheckBox categoryTMWithCheckBox=new CategoryTMWithCheckBox();
                categoryTMWithCheckBox.setId(categoryDTO.getId());
                categoryTMWithCheckBox.setName(categoryDTO.getName());
                categoryTMWithCheckBox.setCheckBox(new CheckBox());
                categoryTMWithCheckBoxes.add(categoryTMWithCheckBox);
            }
            tblCategory.setItems(FXCollections.observableList(categoryTMWithCheckBoxes));
        } catch (CategoryException e) {
            throw new RuntimeException(e);
        }
    }

    public void visualize(){
        clmCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmOption.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        clmAuthorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAuthorOption.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void setDataToComboBox(){
        //publisher
        cmdPublisher.setConverter(new StringConverter<PublisherCM>() {
            @Override
            public String toString(PublisherCM publisherCM) {
                return publisherCM.getName();
            }

            @Override
            public PublisherCM fromString(String s) {
                return null;
            }
        });

        //category
        cmdMainCategory.setConverter(new StringConverter<CategoryCM>() {
            @Override
            public String toString(CategoryCM categoryCM) {
                return categoryCM.getName();
            }

            @Override
            public CategoryCM fromString(String s) {
                return null;
            }
        });
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        BookDTO bookDTO=collectBookData();
        try {
            boolean add = bookService.add(bookDTO);
            if (add){
                new Alert(Alert.AlertType.INFORMATION,"Student Added succesfully").show();
                loadTable();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Student Added failed").show();
            }
        } catch (SuperException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        //delete book_author details
        //delete sub category details
        //add new author details
        //add new sub category details

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnManageCategoryOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Sub/Manage_Category.fxml"));
            Parent load = loader.load();
            ManageCategoryController controller = loader.getController();
            controller.setManageCategoryController(this);
            Scene scene = new Scene(load);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);//this code help to not access manage book window while the category window pop up
            stage.initOwner(txtBookId.getScene().getWindow());//this code help to not access manage book window while the category window pop up
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookDTO collectBookData(){
        int bookId = 0;
        String bookName = txtBookName.getText();
        String isbn = txtBookIsbn.getText();
        float price=0;

        try {
            bookId = Integer.parseInt(txtBookId.getText());
        }catch (NumberFormatException e){
        }

        try {
            price = Float.parseFloat(txtBookPrice.getText());
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Please enter a book price");
            return null;
        }

        int publisherId=0;
        int categoryId=0;
        int count=0;
        try {
            publisherId=cmdPublisher.getSelectionModel().getSelectedItem().getId();
            count++;
            categoryId=cmdMainCategory.getSelectionModel().getSelectedItem().getId();
        }catch (NullPointerException e){
            String item=count==0?"Publisher":"Category";
            new Alert(Alert.AlertType.ERROR,"Please select a "+item);
        }

        List<Integer> autherherIds = tblAuthor.getItems().stream().filter(e -> e.getCheckBox().isSelected()).map(e -> e.getId()).toList();
        List<Integer> subcategoryIds = tblCategory.getItems().stream().filter(e -> e.getCheckBox().isSelected()).map(e -> e.getId()).toList();

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);
        bookDTO.setName(bookName);
        bookDTO.setIsbn(isbn);
        bookDTO.setPrice(price);
        bookDTO.setPublisherId(publisherId);
        bookDTO.setMainCategoryId(categoryId);
        bookDTO.setAuthorerIds(autherherIds);
        bookDTO.setSubCategoryIds(subcategoryIds);

        return bookDTO;
    }
}
