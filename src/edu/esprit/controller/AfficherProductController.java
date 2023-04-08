/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.ProductDao;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AfficherProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> price;
    @FXML
    private TableColumn<Product, String> stock;
        @FXML
    private TableColumn<Product, ImageView> image;
        @FXML
    private Button update;
         @FXML
    private Button supprimer;
 @FXML
    private Button back;
    @FXML
    private TextField nameU;
    @FXML
    private TextField descriptionU;
    @FXML
    private TextField priceU;
    @FXML
    private TextField stockU;
    @FXML
    private TextField imageU;
  
    private ListData listdata = new ListData();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        productTable.setItems(listdata.getProducts());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
    } 
    
        @FXML
        public void update(javafx.scene.input.MouseEvent event) {

        ProductDao pdao=new ProductDao();
        Product p=new Product();
        p=productTable.getSelectionModel().getSelectedItem();
        p.setName(productTable.getSelectionModel().getSelectedItem().getName());
        p.setDescription(productTable.getSelectionModel().getSelectedItem().getDescription());
        p.setPrice(productTable.getSelectionModel().getSelectedItem().getPrice());
        p.setStock(productTable.getSelectionModel().getSelectedItem().getStock());
        p.setImage(productTable.getSelectionModel().getSelectedItem().getImage());
        pdao.update(p);
              
        
    }
        
    public void delete(){
    ProductDao pdao =new ProductDao();
    pdao.delete(productTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(productTable.getSelectionModel().getSelectedItem().getId());
    }
   @FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItem());
   System.out.println(productTable);
          
    }

    @FXML
    public void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
}
