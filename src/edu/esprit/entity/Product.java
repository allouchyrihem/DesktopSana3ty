/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author asus
 */
public class Product {
    
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;   
    private SimpleStringProperty price;
    private SimpleStringProperty stock;
    private SimpleStringProperty image;
    private Category category;


    public Product() {
    }

    public Product(int id, String name, String description, String price, String stock, String image,Category category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleStringProperty(price);
        this.stock = new SimpleStringProperty(stock);
        this.image = new SimpleStringProperty(image);
        this.category = new Category();    }

    public Product(String name, String description, String price, String stock, String image,Category category) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleStringProperty(price);
        this.stock = new SimpleStringProperty(stock);
        this.image = new SimpleStringProperty(image);
        this.category = new Category();    
    }
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }

    public String getStock() {
        return stock.get();
    }

    public void setStock(String stock) {
        this.stock = new SimpleStringProperty(stock);
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image = new SimpleStringProperty(image);
    }
      public String getCategory(){
        return category.getName();
    }
     public void setCategory(Category category){
         this.category=category;
     }

    public SimpleStringProperty getNameProperty(){
        return name;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getPriceProperty(){
        return price;
    }
    public SimpleStringProperty getStockProperty(){
        return stock;
    }
    
    public SimpleStringProperty getImageProperty(){
        return image;
    }
    public Category getCategoryProperty(){
        return category;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock=" + stock + ", image=" + image +", category="+category+'}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
    
}

