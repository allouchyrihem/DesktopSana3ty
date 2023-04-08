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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCategoryController implements Initializable {
@FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Integer> id;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, String> description;
    private ListData listdata = new ListData();
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
         back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        categoryTable.setItems(listdata.getcategories());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    }
    @FXML
        public void update(javafx.scene.input.MouseEvent event) {

        CategoryDao cdao=new CategoryDao();
        Category p=new Category();
        p=categoryTable.getSelectionModel().getSelectedItem();
        p.setName(categoryTable.getSelectionModel().getSelectedItem().getName());
        p.setDescription(categoryTable.getSelectionModel().getSelectedItem().getDescription());
        cdao.update(p);
              
        
    }
    public void delete(){
    CategoryDao cdao =new CategoryDao();
    cdao.delete(categoryTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(categoryTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   categoryTable.getItems().removeAll(categoryTable.getSelectionModel().getSelectedItem());
   System.out.println(categoryTable);
          
    }

    @FXML
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
    }    
    

