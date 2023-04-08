/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCategoryController implements Initializable {
  @FXML
    private Button btnC;
    @FXML
    private TextField nameC;
    @FXML
    private TextField descriptionC;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         btnC.setOnAction((ActionEvent event) -> {
            
            Category p = new Category(nameC.getText(), descriptionC.getText());
            CategoryDao cdao = CategoryDao.getInstance();
            cdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Category insérée avec succés!");
        alert.show();
        nameC.setText("");
        descriptionC.setText("");

        });
        
        
    

    }    
    
}
