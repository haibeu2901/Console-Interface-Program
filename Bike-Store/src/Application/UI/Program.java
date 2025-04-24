/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.UI;

import Application.Utilities.DataInput;
import BussinessLayer.Service.ProductService;
import BussinessLayer.Service.IService;
import java.util.Collections;

/**
 *
 * @author beu29
 */
public class Program {

    private static final String BRAND_DATA = "Brand.txt";
    private static final String CATEGORY_DATA = "Category.txt";
    private static final String PRODUCT_DATA = "Product.txt";

    public static void main(String[] args) {

//        System.out.println(String.join("", Collections.nCopies(10, "**********")));
        try {
            IService productService = new ProductService(PRODUCT_DATA);
            Menu.manageProduct(productService, BRAND_DATA, CATEGORY_DATA);
        } catch (Exception e) {
            System.out.println(":"+e.getMessage());
        }
    }
}
