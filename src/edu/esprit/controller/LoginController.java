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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class LoginController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private TextField pwdField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpBtn.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/pidev/view/SignUp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
     loginBtn.setOnAction(event -> {
            if (emailField.getText().trim().isEmpty()
                    || pwdField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
            }else if(!isValidEmailFormat(emailField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L'email fournit n'est pas valide.");
                alert.show();
            }
            else {
      
                UserDao udao = UserDao.getInstance();
                User user = udao.findUserByEmail(emailField.getText().trim());
                if ( user == null){
                    System.out.println("No user found");
                    
                }else {
                    System.out.print(user);
                }
                emailField.setText("");
                pwdField.setText("");
               
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


