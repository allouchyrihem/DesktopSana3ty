/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.pidev.dao.UserDao;
import com.pidev.entity.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class UsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<User> usersList;

     @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, List<String>> roleCol;

    @FXML
    private TableColumn<User, Boolean> statusCol;
    
    @FXML
    private Button validateBtn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Button delBtn;

    @FXML
    private Button detailsBtn;
    
    private ListData listdata = new ListData();
    
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usersList.setItems(listdata.getPersons());
       nameCol.setCellValueFactory(cell -> cell.
                getValue().getNameProperty());
       emailCol.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
       statusCol.setCellValueFactory(cell -> cell.
                getValue().getStatusProperty());
}
  
    public void validateUser(){
       User selectedUser = usersList.getSelectionModel().getSelectedItem();
       if (selectedUser != null) {
       UserDao u = UserDao.getInstance();
       u.validateUSer(selectedUser);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Inscription validé avec succés!");
                alert.show();
    }  
     
    }
    
    public void refuseUser(){
       User selectedUser = usersList.getSelectionModel().getSelectedItem();
       if (selectedUser != null) {
       UserDao u = UserDao.getInstance();
       u.refuseUser(selectedUser);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Inscription validé avec succés!");
                alert.show();
    }  
     
    }
    
    
}
