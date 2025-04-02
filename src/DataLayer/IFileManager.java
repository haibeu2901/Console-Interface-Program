/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import BussinessLayer.Entity.Product;

/**
 *
 * @author beu29
 */

import java.util.List;

public interface IFileManager { 
    List<String> readDataFromFile()  throws Exception; 
    //To do here
    public void printDataToFile(List<Product> productList) throws Exception;
    public String getBrandName(String id);
    public String getCategoryName(String id);
}
