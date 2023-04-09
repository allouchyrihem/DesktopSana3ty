/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.pidev.dao.UserDao;
import com.pidev.entity.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData_1 {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<User> persons=FXCollections.observableArrayList();

    public ListData_1() {
        
        UserDao pdao=UserDao.getInstance();
        persons= pdao.displayAll();
        System.out.println(persons);
    }
    
    public ObservableList<User> getPersons(){
        return persons;
    }
   
}
