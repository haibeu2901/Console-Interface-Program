/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Service;

import BussinessLayer.Entity.Product;
import DataLayer.ProductDao.IProductDao;
import DataLayer.DaoFactory;
import DataLayer.IDaoFactory;
import java.util.List;

/**
 *
 * @author beu29
 */
public class ProductService implements IService<Product>{
    
    IProductDao productAction;
    IDaoFactory productDaoFactory;

    public ProductService() {
    }
    //--------------------------------------------------

    public ProductService(String inputDataFile) throws Exception {
        productDaoFactory = new DaoFactory(inputDataFile);
        this.productAction = productDaoFactory.productDao();
    }
    //--------------------------------------------------
    @Override
    public void printList() throws Exception {
//        productAction.getList().forEach(obj -> System.out.println(obj));
//        for(var obj : productAction.getList()){
//            System.out.println(obj);
//        }
        productAction.printList();
    }
    //--------------------------------------------------
    public List<Product> getList() throws Exception {
        return productAction.getList();
    }
    //--------------------------------------------------
    public void add() throws Exception {
        productAction.addProduct();
    }

    //To do here    

    @Override
    public List<Product> searchProduct(String name) throws Exception {
        return productAction.searchProductByName(name);
    }

    @Override
    public void updateProduct(String id) throws Exception {
        productAction.updateProduct(id);
    }

    @Override
    public void deleteProduct(String id) throws Exception {
        productAction.deleteProduct(id);
    }

    @Override
    public void saveToFile() throws Exception {
        productAction.saveDataToFile(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newFunction() {
        productAction.newFunction();
    }
    
    
}
