/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.pidev.dao.UserDao;
import com.pidev.entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class SignUpVendeurController implements Initializable {
        @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passField;

    @FXML
    private TextField confPassField;

    @FXML
    private TextField addField;

    @FXML
    private TextArea descField;

    @FXML
    private Button submitBtn;

    @FXML
    private Button connectBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connectBtn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/pidev/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
          submitBtn.setOnAction(event -> {
            if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()
                    || passField.getText().trim().isEmpty() || confPassField.getText().trim().isEmpty() || descField.getText().trim().isEmpty()
                    || addField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
            } else if (!passField.getText().trim().equals(confPassField.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les mots de passe saisis ne correspondent pas.");
                alert.show();
            }else if(!isValidEmailFormat(emailField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L'email fournit n'est pas valide.");
                alert.show();
            }
            else {
                User u = new User(nameField.getText(), emailField.getText(), passField.getText(),addField.getText(),descField.getText());
                UserDao pdao = UserDao.getInstance();
                pdao.insertVendeur(u);
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Personne insérée avec succés!");
                alert.show();
                nameField.setText("");
                emailField.setText("");
                passField.setText("");
                confPassField.setText("");
                addField.setText("");
                descField.setText("");
            }
        });

    }
    
    public static boolean isValidEmailFormat(String email) {
    // Regular expression for email validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                         "[a-zA-Z0-9_+&*-]+)*@" +
                         "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                         "A-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    if (email == null) {
        return false;
    }
    return pattern.matcher(email).matches();
}
}