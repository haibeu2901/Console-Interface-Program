/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import BussinessLayer.Entity.Product;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

/**
 *
 * @author beu29
 */
public class FileManager implements IFileManager {

    private static final String BRAND_DATA = "Brand.txt";
    private static final String CATEGORY_DATA = "Category.txt";
    private String fileName;

    public FileManager() {
    }

    //--------------------------------------------------        
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    //--------------------------------------------------
    @Override
    public List<String> readDataFromFile() throws IOException {
        List<String> result = new ArrayList<>();
        File f = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                result.add(line);
            }
        }
        br.close();
        return result;
    }

    @Override
    public void printDataToFile(List<Product> productList) throws IOException {
        File f = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        for (Product prod : productList) {
            bw.write(prod.toString());
        }
        bw.close();
    }

    @Override
    public String getBrandName(String id) {
        String brandName = null;
        try {
            File f = new File("Brand.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            while ((s = br.readLine()) != null) {
                List<String> temp = Arrays.asList(s.split(", "));
                if (!temp.isEmpty()) {
                    if (temp.get(0).toLowerCase().equalsIgnoreCase(id)) {
                        brandName = temp.get(1);
                        break;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (brandName == null) {
            brandName = id;
        }
        return brandName;
    }

    @Override
    public String getCategoryName(String id) {
        String categoryName = null;
        try {
            File f = new File("Category.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            while ((s = br.readLine()) != null) {
                List<String> temp = Arrays.asList(s.split(", "));
                if (!temp.isEmpty()) {
                    if (temp.get(0).toLowerCase().equalsIgnoreCase(id)) {
                        categoryName = temp.get(1);
                        break;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (categoryName == null) {
            categoryName = id;
        }
        return categoryName;
    }
}
