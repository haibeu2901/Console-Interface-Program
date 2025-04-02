/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Entity;

import BussinessLayer.Components.DataValidation;

/**
 *
 * @author beu29
 */
public class Product {

    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int year;
    private double price;
    private String brandFile;
    private String categoryFile;

    public Product() {
    }

    public Product(String id, String name, String brandId, String categoryId, int year, double price) {
        this.id = id.trim().toUpperCase();
//        setId(id);
        this.name = name.trim();
//        setName(name);
        this.brandId = brandId.trim();
//        setBrandId(brandId);
        this.categoryId = categoryId.trim();
//        setCategoryId(categoryId);
        this.year = year;
//        setYear(year);
        this.price = price;
//        setPrice(price);
    }

    public Product(String id, String name, String brandId, String categoryId, int year, double price, String brandInputFile, String categoryInputFile) throws Exception {
        setId(id);
//        this.id = id;
        setName(name);
//        this.name = name;
        setBrandId(brandId);
//        this.brandId = brandId;
        setCategoryId(categoryId);
//        this.categoryId = categoryId;
        setYear(year);
//        this.year = year;
        setPrice(price);
//        this.price = price;
        this.brandFile = brandInputFile;
        this.categoryFile = categoryInputFile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (DataValidation.checkStringEmpty(name)) {
            System.out.println(">>>Name can not be empty!");
        }
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
//        if (!DataValidation.checkIdExist(brandId, "Brand.txt")) {
//            System.out.println(">>>Brand id does not exist!");
//        }
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
//        if (!DataValidation.checkIdExist(categoryId, "Category.txt")) {
//            System.out.println(">>>Category id does not exist!");
//        }
        this.categoryId = categoryId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (!DataValidation.checkYear(year)) {
            System.out.println(">>>Invalid Year");
        }
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (!DataValidation.checkPosNum(price)) {
            System.out.println(">>>Price must be positive!");
        }
        this.price = price;
    }

    public void setId(String id) {
        if (!DataValidation.checkId(id.toUpperCase())) {
            System.out.println(">>>Invalid id. The correct format: BIxxx.");
        }
        this.id = id.toUpperCase();
    }

    public String toTiteCase(String value) {
        String s = "";
        value = value.trim().replaceAll("\\s+", " ").toLowerCase();
        String[] words = value.split(" ");
        for (String word : words) {
            char[] arr = word.toCharArray();
            arr[0] = Character.toUpperCase(arr[0]);
            s = s + new String(arr) + " ";
        }
        return s.trim();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %.2f \n", getId(), getName(), getBrandId(), getCategoryId(), getYear(), getPrice());
    }

}
