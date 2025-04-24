/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.UI;

import Application.Utilities.DataInput;
import BussinessLayer.Entity.Product;
import BussinessLayer.Service.IService;
import DataLayer.IFileManager;
import java.util.List;

/**
 *
 * @author beu29
 */
public class ProductMenu {

    IService<Product> service;

    //--------------------------------------------------  
    public ProductMenu(IService<Product> service) {
        this.service = service;
    }

    //--------------------------------------------------  
    public void processMenuForProduct(String brandInputFile, String categoryInputFile) {
        boolean stop = true;
        String ask;
        try {
            do {
                Menu.print("******Product Management******|1.Add Product|2.Update Product|3.Remove Product"
                        + "|4.Find Product|5.Print Product List|6.Export to file|7.New function|Others - Exit|Select :");
                String choice = Menu.getUserChoice();
                switch (choice) {
                    case "1":
                        do {
                            addNewProduct();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "2":
                        do {
                            updateProduct();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "3":
                        do {
                            removeProduct();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "4":
                        do {
                            searchProduct();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "5":
                        do {
                            System.out.println(">>Product List:");
                            printList();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "6":
                        do {
                            saveToFile();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    case "7":
                        do {
                            newFunction();
                            ask = DataInput.getString("Countinue? (Y/N): ");
                        } while (ask.equalsIgnoreCase("y"));
                        break;
                    default:
                        stop = false;
                        System.out.println(">>Thank you for using.");
                        break;
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //--------------------------------------------------    
//    public Product getProduct() throws Exception {
//        String id = DataInput.getString("Enter product id:");
//        String name = DataInput.getString("Enter product name:");
//        String brandId = DataInput.getString("Enter brand id:");
//        String categoryId = DataInput.getString("Enter category id:");
//        int year = DataInput.getIntegerNumber("Enter model year:");
//        double price = DataInput.getDoubleNumber("Enter product price:");
//        return new Product(id, name, brandId, categoryId, year, price);
//    }
    //--------------------------------------------------  
    public void addNewProduct() {
        try {
//            Product newProduct = getProduct();
            service.add();
            System.out.println(">>Product added successfully.");
        } catch (Exception e) {
            System.out.println(">>" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    //To do here..........
    public void updateProduct() throws Exception {
        String id = DataInput.getString("Enter product id: ");
        service.updateProduct(id);
    }

    public void removeProduct() throws Exception {
        String id = DataInput.getString("Enter product id: ");
        service.deleteProduct(id);
    }

    public void searchProduct() throws Exception {
        String name = DataInput.getString("Enter product name: ");
        List<Product> list = service.searchProduct(name);
        if (list.isEmpty()) {
            System.out.println(">>>Have no any product.");
        } else {
            for (Product p : list) {
                System.out.print(p.toString());
            }
        }
    }

    public void saveToFile() throws Exception {
        service.saveToFile();
        System.out.println(">>>Save to file successfully!");
    }

    public void printList() throws Exception {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Product ID", "Name", "Brand Name", "Category Name", "Model Year", "Price");
        System.out.println("-------------------------------------------------------------------------------------------");
        service.printList();
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public void newFunction() {
        service.newFunction();
    }

}
