/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author asus
 */
public class AjouterProductController  implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;
    @FXML
    private Button addImage;
    private File f = null;
    @FXML
    private ImageView ImageView;
    List<String> lstFile;
    @FXML
    private ComboBox<String> category;
    private ListData listdata = new ListData();
    private List<String> categories;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    public String imageEncoderDecoder(String path) throws IOException {

    // read image from file
    FileInputStream stream = new FileInputStream(path);

    // get byte array from image stream
    int bufLength = 2048;
    byte[] buffer = new byte[2048];
    byte[] data;

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int readLength;
    while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
        out.write(buffer, 0, readLength);
    }

    data = out.toByteArray();
    String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

    out.close();
    stream.close();
    return imageString;
}
  @FXML
    void SingleImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("jpg and png file",lstFile));
        f = fc.showOpenDialog(null);
        if (f!= null ){
            addImage.setText(f.getName());
            ImageView.setImage(new Image("file:" + f));
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        btn.setOnAction((ActionEvent event) -> {
            categories=listdata.getcatByname();
            category.setItems((ObservableList<String>) categories);
            Product p;
            try {
                p = new Product(name.getText(), description.getText(),price.getText(),stock.getText(),imageEncoderDecoder(f.getAbsolutePath()), (Category) categories);
                ProductDao pdao = ProductDao.getInstance();
                pdao.insert(p);
            } catch (IOException ex) {
                Logger.getLogger(AjouterProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit insérée avec succés!");
        alert.show();
        name.setText("");
        description.setText("");
        price.setText("");
        stock.setText("");
        addImage.setText("");
        ImageView.setImage(null);
        category.setItems(null);
        });
       
        lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.PNG");
        lstFile.add("*.jpg");
        lstFile.add("*.JPEG");
        
    }
    
  

}

