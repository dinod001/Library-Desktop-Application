package com.CodesageLK.Controller.sub;

import com.CodesageLK.repo.Custom.Impl.BookRepoImpl;
import com.CodesageLK.repo.utill.RepoFactory;
import com.CodesageLK.repo.utill.RepoTypes;
import com.CodesageLK.service.Custom.Impl.BookServiceImpl;
import com.CodesageLK.service.utill.RepoServiceFactory;
import com.CodesageLK.service.utill.RepoServiceTypes;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageBooksController {
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtBookIsbn;
    public TextField txtBookPrice;
    public ComboBox cmdPublisher;
    public ComboBox cmdMainCategory;
    public TableView tblCategory;
    public TableColumn clmCategoryName;
    public TableColumn clmOption;
    public TableView tblPublisher;
    public TableColumn clmPublisherName;
    public TableColumn clmPublisherOption;
    public TableView tblBook;
    public TableColumn clmID;
    public TableColumn clmName;
    public TableColumn clmIsbn;
    public TableColumn clmPrice;

   /* BookRepoImpl bookRepo= RepoFactory.getInstance().getRepo(RepoTypes.BOOK_REPO);
    BookServiceImpl bookService=new BookServiceImpl(bookRepo);*/

    private BookServiceImpl bookServiceImpl= RepoServiceFactory.getInstance().getService(RepoServiceTypes.BOOK_SERVICE);

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }
}
