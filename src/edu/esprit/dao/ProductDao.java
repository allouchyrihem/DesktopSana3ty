/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.entity.Product;
import edu.esprit.util.MyConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ProductDao implements Idao<Product>{
    
    private static ProductDao instance;
    private Statement st;
    private ResultSet rs;
    
    public ProductDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProductDao getInstance(){
        if(instance==null) 
            instance=new ProductDao();
        return instance;
    }

    @Override
    public void insert(Product o) {
        String req="insert into product (name,description,price,stock,image) values ('"+o.getName()+"','"+o.getDescription()+"','"+o.getPrice()+"','"+o.getStock()+"','"+o.getImage()+"','"+o.getCategory()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(int id) {
        String req="delete from product where id="+id;
        Product p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Product> displayAll() {
        String req="select * from product";
        ObservableList<Product> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getString("price"));
                p.setStock(rs.getString("stock"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> displayAllList() {
        String req="select * from product";
        List<Product> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getString("price"));
                p.setStock(rs.getString("stock"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Product displayById(int id) {
           String req="select * from product where id ="+id;
           Product p=new Product();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getString("price"));
                p.setStock(rs.getString("stock"));
                p.setImage(rs.getString("image"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Product p) {
        String qry="UPDATE product SET name = "+p.getName()+"',description ="+p.getDescription()+"', price ="+p.getPrice()+"', stock ="+p.getStock()+"', image ="+p.getImage()+"' WHERE id ="+p.getId();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
}
