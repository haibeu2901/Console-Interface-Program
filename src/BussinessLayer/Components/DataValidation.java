/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BussinessLayer.Components;

import java.io.*;
import java.util.*;

/**
 *
 * @author beu29
 */
public class DataValidation {

    //------------------------------------------------------
    public static boolean checkNumberInMinMax(int number, int min, int max) {
        boolean result = true;
        if (number < min || number > max) {
            result = false;
        }
        return result;
    }

    //------------------------------------------------------
    public static boolean checkStringEmpty(String value) {
        boolean result = false;
        if (value.isEmpty()) {
            result = true;
        }
        return result;
    }

    //------------------------------------------------------
    public static boolean checkStringLengthInRange(String value, int min, int max) {
        boolean result = true;
        int length;
        if (!checkStringEmpty(value)) {
            result = false;
        } else {
            length = value.length();
            if (length < min || length > max) {
                result = false;
            }
        }
        return result;
    }
    //------------------------------------------------------

    public static boolean checkStringWithFormat(String value, String pattern) {
        boolean result = false;
        if (value.matches(pattern)) {
            result = true;
        }
        return result;
    }

    //--------------------------------------------------  
    //To do here..........
    public static boolean checkYear(int year) {
        boolean result = false;
        if (year > 0) {
            result = true;
        }
        return result;
    }

    public static boolean checkPosNum(double price) {
        boolean result = false;
        if (price >= 0) {
            result = true;
        }
        return result;
    }

    public static boolean checkId(String id) {
        boolean check = false;

        if (id.matches("BI\\d{3}")) {
            check = true;
        }

        return check;
    }

    public static boolean checkIdExist(String id, String inputFile) {
        boolean check = false;

        try {
            File f = new File(inputFile);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String str;
            while ((str = br.readLine()) != null) {
                List<String> temp = Arrays.asList(str.split(", "));
                if (temp.get(0).equals(id)) {
                    check = true;
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return check;
    }

}
