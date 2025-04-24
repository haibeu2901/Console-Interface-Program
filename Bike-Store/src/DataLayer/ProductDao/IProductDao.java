/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.ProductDao;

import java.util.*;


/**
 *
 * @author beu29
 */
// defines methods to access orders.
// this is a database-independent interface. 
// Implementations are database specific
// ** DAO Pattern
public interface IProductDao<Product> {    
    void loadDataFromFile() throws Exception ;   
    void addProduct() throws Exception;
    List<Product> searchProductByName(String name) throws Exception;
    void deleteProduct(String id);
    void saveDataToFile() throws Exception;
    void addNew(Product prod)throws Exception;
    List<Product> getList() throws Exception;
    void printList() throws Exception;
    void updateProduct(String id) throws Exception;
    List<Product> sortByPrice(List<Product> productList) throws Exception;
    List<Product> sortByYear(List<Product> productList) throws Exception;
    void newFunction();
}
