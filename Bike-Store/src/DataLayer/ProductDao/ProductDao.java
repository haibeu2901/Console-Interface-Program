/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.ProductDao;

import Application.Utilities.DataInput;
import BussinessLayer.Entity.Product;
import java.util.*;
import DataLayer.IFileManager;
import BussinessLayer.Components.DataValidation;

/**
 *
 * @author beu29
 */
public class ProductDao implements IProductDao<Product> {

    IFileManager fileManager;
    List<Product> productList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    final String BRAND_FILE = "Brand.txt";
    final String CATEGORY_FILE = "Category.txt";

    public ProductDao() {
    }

    public ProductDao(IFileManager fileManager) throws Exception {
        this.fileManager = fileManager;
        loadDataFromFile();
    }

    @Override
    public void loadDataFromFile() throws Exception {
        String id, name, brandId, categoryId;
        int year;
        double price;
        List<String> prodData = fileManager.readDataFromFile();
        for (String c : prodData) {
            List<String> prod = Arrays.asList(c.split(","));
            id = prod.get(0).trim();
            name = prod.get(1).trim();
            brandId = prod.get(2).trim();
            categoryId = prod.get(3).trim();
            year = Integer.parseInt(prod.get(4).trim());
            price = Double.parseDouble(prod.get(5).trim());
            Product newProduct = new Product(id, name, brandId, categoryId, year, price);
            addNew(newProduct);
        }
    }

    public List<Product> getList() throws Exception {
        if (productList.isEmpty()) {
            throw new Exception("Product list is empty.");
        }
        return productList;
    }

    @Override
    public void addNew(Product product) throws Exception {
        productList.add(product);
    }

    @Override
    public void addProduct() throws Exception {
        boolean stop = true;
        String id, name, brandId, categoryId;
        int year;
        double price;

        do {
            boolean found = false;
            id = DataInput.getString("Enter product id: ").toUpperCase();
            if (getList() != null) {
                for (Product p : getList()) {
                    if (id.equals(p.getId())) {
                        System.out.println(">>> ID already exists!");
                        found = true;
                        break;
                    }
                }
            }
            if (!DataValidation.checkId(id)) {
                System.out.println(">>> Invalid ID format! ID must follow the format BI###.");
            } else if (!found) {
                stop = false;
            }
        } while (stop);

        do {
            stop = true;
            name = DataInput.getString("Enter name: ");
            if (DataValidation.checkStringEmpty(name)) {
                System.out.println(">>> Name cannot be empty!");
            } else {
                stop = false;
            }
        } while (stop);

        do {
            stop = true;
            brandId = DataInput.getString("Enter brand id: ").toUpperCase();
            if (DataValidation.checkStringEmpty(brandId)) {
                System.out.println(">>> Brand ID cannot be empty!");
            } else {
                stop = false;
            }
        } while (stop);

        do {
            stop = true;
            categoryId = DataInput.getString("Enter category id: ").toUpperCase();
            if (DataValidation.checkStringEmpty(categoryId)) {
                System.out.println(">>> Category ID cannot be empty!");
            } else {
                stop = false;
            }
        } while (stop);

        do {
            stop = true;
            year = DataInput.getIntegerNumber("Enter model year: ");
            if (!DataValidation.checkYear(year)) {
                System.out.println(">>> Invalid year!");
            } else {
                stop = false;
            }
        } while (stop);

        do {
            stop = true;
            price = DataInput.getDoubleNumber("Enter price: ");
            if (!DataValidation.checkPosNum(price)) {
                System.out.println(">>> Price must be a positive number.");
            } else {
                stop = false;
            }
        } while (stop);

        Product product = new Product(id, name, brandId.toUpperCase(), categoryId.toUpperCase(), year, price);
        addNew(product);
    }

    @Override
    public List<Product> searchProductByName(String name) throws Exception {
        List<Product> searchList = new ArrayList<>();
        for (Product prod : productList) {
            if (prod.getName().toLowerCase().contains(name.toLowerCase())) {
                searchList.add(prod);
            }
        }
        return sortByYear(searchList);
    }

    @Override
    public void deleteProduct(String id) {
        boolean found = false;
        for (Product prod : productList) {
            if (prod.getId().equalsIgnoreCase(id)) {
                found = true;
                System.out.println(prod);
                while (true) {
                    System.out.println("Delete this product permanent? Y/N: ");
                    String choice = sc.nextLine().toUpperCase();
                    switch (choice) {
                        case "Y":
                            productList.remove(prod);
                            System.out.println(">>>Delete successfully!");
                            return;
                        case "N":
                            System.out.println(">>>Canceled delete product!");
                            return;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }
        }
        if (!found) {
            System.out.println(">>> Product does not exist!");
        }
    }

    @Override
    public void saveDataToFile() throws Exception {
        fileManager.printDataToFile(productList);
    }

    @Override
    public void printList() throws Exception {
        if (!productList.isEmpty()) {
            sortByPrice(productList);
            for (Product prod : productList) {
                String id = prod.getId();
                String name = prod.getName();
                String brandName = fileManager.getBrandName(prod.getBrandId());
//                String brandId = prod.getBrandId();
                String categoryName = fileManager.getCategoryName(prod.getCategoryId());
//                String categoryId = prod.getCategoryId();
                int year = prod.getYear();
                double price = prod.getPrice();

                System.out.printf("%-15s %-15s %-15s %-15s %-15d %-15.2f\n", id, name, brandName, categoryName, year, price);
//                System.out.printf("%s, %s, %s, %s, %d, %.2f\n", id, name, brandId, categoryId, year, price);
            }
        } else {
            System.out.println(">>>List empty!");
        }
    }

    @Override
    public void updateProduct(String id) throws Exception {
        String update = "";
        boolean found = false;
        List<Product> pList = productList;
        for (Product prod : productList) {
            if (prod.getId().equalsIgnoreCase(id)) {
                found = true;
                while (true) {
                    System.out.print("******Update Fields******\n 1.ID\n 2.Name\n 3.Brand ID\n 4.Category ID\n 5.Model Year\n 6.Price\n Others - Back to main menu\n Select: ");
                    String choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.print("Enter new id: ");
                            update = sc.nextLine();
                            for (Product p : pList) {
                                if (update.equalsIgnoreCase(p.getId())) {
                                    System.out.println(">>> ID already exists!");
                                    return;
                                }
                            }
                            if (!update.isEmpty()) {
                                prod.setId(update);
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update id!");
                            }
                            return;
                        case "2":
                            System.out.print("Enter new name: ");
                            update = sc.nextLine();
                            if (!update.isEmpty()) {
                                prod.setName(update);
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update name!");
                            }
                            return;
                        case "3":
                            System.out.print("Enter new brand id: ");
                            update = sc.nextLine();
                            if (!update.isEmpty()) {
                                prod.setBrandId(update.toUpperCase());
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update brand id!");
                            }
                            return;
                        case "4":
                            System.out.print("Enter new category id: ");
                            update = sc.nextLine();
                            if (!update.isEmpty()) {
                                prod.setCategoryId(update);
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update category id!");
                            }
                            return;
                        case "5":
                            System.out.print("Enter new model year: ");
                            update = sc.nextLine();
                            if (!update.isEmpty()) {
                                prod.setYear(Integer.parseInt(update));
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update model year!");
                            }
                            return;
                        case "6":
                            System.out.print("Enter new price: ");
                            update = sc.nextLine();
                            if (!update.isEmpty()) {
                                prod.setPrice(Double.parseDouble(update));
                                System.out.println(">>> Update Successfully!");
                            } else {
                                System.out.println(">>> Cancel update price!");
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }
        if (!found) {
            System.out.println(">>> Product does not exist!");
        }
    }

    @Override
    public List<Product> sortByPrice(List<Product> productList) throws Exception {
        Collections.sort(productList, (Product p1, Product p2) -> {
            if (p1.getPrice() < p2.getPrice()) {
                return 1;
            } else if (p1.getPrice() > p2.getPrice()) {
                return -1;
            } else {
                return (p1.getName().compareTo(p2.getName()));
            }
        });
        return productList;
    }

    @Override
    public List<Product> sortByYear(List<Product> productList) throws Exception {
        Collections.sort(productList, (Product p1, Product p2) -> {
            if (p1.getYear() > p2.getYear()) {
                return 1;
            } else if (p1.getYear() == p2.getYear()) {
                return 0;
            } else {
                return -1;
            }
        });
        return productList;
    }

//    public CompanyManagement sortEmployees() {
////        CompanyManagement result = new CompanyManagement();
////        for (Employee emp : this) {
////            result.add(emp);
////        }
//        CompanyManagement result = (CompanyManagement) this.clone();
//
//        Comparator com = new Comparator<Employee>() {
//            public int compare(Employee e1, Employee e2) {
//                int c = (int) (e1.getSalary() - e2.getSalary());
//                if (c == 0) {
//                    c = e1.getEmpName().compareTo(e2.getEmpName());
//                }
//                return c;
//            }
//        };
//        Collections.sort(result, com);
//        return result;
//    }
    
//    {    
//        int year = Integer.parseInt(DataInput.getString("Input year: "));
//        int count = 0;
//        String input = DataInput.getString("Enter category name: ");
//        List<Product> newList = new ArrayList<>();
//        
//        for (Product p : productList) {
//            if (p.getYear() >= year) {
//                newList.add(p);
//                count++;
//            }
//        }

//        for (Product p : productList) {
////            String brandName = fileManager.getBrandName(p.getBrandId());
//            String categoryName = fileManager.getCategoryName(p.getCategoryId());
//            if (categoryName.toUpperCase().contains(input.toUpperCase())) {
//                count++;
//                newList.add(p);
//            }
//        }
//      }
    
    public List<Product> sortPriceDesc(List<Product> productList) {
        Collections.sort(productList, (Product p1, Product p2) -> {
            if (p1.getPrice() < p2.getPrice()) {
                return 1;
            } else if (p1.getPrice() == p2.getPrice()) {
                return 0;
            } else {
                return -1;
            }
        });
        return productList;
    }

    public List<Product> sortByNameAsc(List<Product> productList) {
        List<Product> newList = productList;
        Comparator com = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                int c = (Integer) p1.getName().compareTo(p2.getName());
                return c;
            }
        };
        Collections.sort(newList, com);
        return newList;
    }

    public List<Product> searchProductByYear(int year) {
        List<Product> nList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getYear() >= year) {
                nList.add(p);
            }
        }
        nList.sort(Comparator.comparingDouble(Product::getPrice).reversed().thenComparing(Product::getName));
        return nList;
//        return sortPriceDesc(nList);
    }
    
//    PRINT EXAMPLE
//    
//        if (count != 0) {
//            System.out.println("");
//            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Product ID", "Name", "Brand ID", "Category ID", "Model Year", "Price");
//            System.out.println("-------------------------------------------------------------------------------------------");
//
//            for (Product prod : newList) {
//                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", prod.getId(), prod.getName(), prod.getBrandId(), prod.getCategoryId(), prod.getYear(), prod.getPrice());
//            }
//
//            System.out.println("-------------------------------------------------------------------------------------------");
//            System.out.printf(">>> Found %d product.\n\n", count);
//
//        } else {
//            System.out.println(">>> Found no product.\n");
//        }

    @Override
    public void newFunction() {
        
        double price = Double.parseDouble(DataInput.getString("Input update price: "));
        String name = DataInput.getString("Input brand name: ");
        List<Product> newList = new ArrayList<>();
        int count = 0;
        
        for (Product p : productList) {
            String brandName = fileManager.getBrandName(p.getBrandId());
            if (brandName.equalsIgnoreCase(name)) {
                p.setPrice(price);
                count++;
                newList.add(p);
            }
        }
        
        if (count != 0) {
            System.out.println("");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Product ID", "Name", "Brand ID", "Category ID", "Model Year", "Price");
            System.out.println("-------------------------------------------------------------------------------------------");

            for (Product prod : newList) {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", prod.getId(), prod.getName(), prod.getBrandId(), prod.getCategoryId(), prod.getYear(), prod.getPrice());
            }

            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf(">>> Updated %d product.\n\n", count);

        } else {
            System.out.println("");
            System.out.println(">>> Found no product to update.\n");
        }
        
    }

}
