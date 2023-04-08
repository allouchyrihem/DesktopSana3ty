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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ListData {
      
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Product> products=FXCollections.observableArrayList();
    private ObservableList<Category> categories=FXCollections.observableArrayList();
    private ObservableList<String> cat=FXCollections.observableArrayList();
    public ListData() {
        
        ProductDao pdao=ProductDao.getInstance();
        CategoryDao cdao=CategoryDao.getInstance();
        products= (ObservableList<Product>)pdao.displayAll();
        categories= (ObservableList<Category>)cdao.displayAll();
        cat=(ObservableList<String>)cdao.displayName();
    }
    
    public ObservableList<Product> getProducts(){
        return products;
    }
    public ObservableList<Category> getcategories(){
        return categories;
    }
    public List<String> getcatByname(){
        return cat;
    }
}
