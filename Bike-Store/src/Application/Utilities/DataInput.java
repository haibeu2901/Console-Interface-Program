/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities;

import java.util.Scanner;

/**
 *
 * @author beu29
 */
public class DataInput {

    //-------------------------------------------------
    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("\\d{1,10}") == false) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    //---------------------------------------------------
    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("\\d{1,10}") == false) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    //--------------------------------------------------
    public static String getString(String displayMessage) {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.print(displayMessage);
        s = sc.nextLine();
        return s;
    }

    //---------------------------------------------------
    public static String getString() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        return s;
    }
    //--------------------------------------------------  
    //To do here..........
    public static double getDoubleNumber(String displayMessage) throws Exception {
        double number = 0;
        String s;
        System.out.print(displayMessage);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("\\d+(\\.\\d+)?") == false) {
            throw new Exception("Data invalid.");
        } else {
            number = Double.parseDouble(s);
        }
        return number;
    }

    //---------------------------------------------------
    public static double getDoubleNumber() throws Exception {
        double number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if (s.matches("[0-9](.[0-9]*)") == false) {
            throw new Exception("Data invalid.");
        } else {
            number = Double.parseDouble(s);
        }
        return number;
    }

}
