/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.UI;

import Application.Utilities.DataInput;
import BussinessLayer.Service.IService;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author beu29
 */
public class Menu {

    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    public static String getUserChoice() {
        String input = "";
        try {
            input = DataInput.getString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return input;
    }

    public static void manageProduct(IService service, String brandInputFile, String categoryInputFile) {
        ProductMenu productMenu = new ProductMenu(service);
        productMenu.processMenuForProduct(brandInputFile, categoryInputFile);
    }

}
