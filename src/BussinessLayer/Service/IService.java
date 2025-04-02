/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Service;

import java.util.List;

/**
 *
 * @author beu29
 */
public interface IService<Product> {
    //Repository      
    void printList() throws Exception ;   
    List<Product> getList() throws Exception;    
    void add() throws Exception;    
    //Producthe other repositories
    //.....
    List<Product> searchProduct(String name) throws Exception;
    void updateProduct(String id) throws Exception;
    void deleteProduct(String id) throws Exception;
    void saveToFile() throws Exception;
    void newFunction();
}
