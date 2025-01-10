/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg06_Code;

/**
 * @author Duc Ta
 */
public class Char {

    public static void main(String[] args) {
        char c1 = 'A';             // character literal
        char c2 = 65;              // ASCII code value in decimal
        char c3 = '\u0041';        // ASCII code value in Unicode value
        char c4 = (char) 0XAB0041; // lower 16 bits hex code 0041
        char c5 = (char) 65.99;    // decimal 65.99

        System.out.println(c1 + " " + c2 + " " + c3 + " " + c4 + " " + c5);
    }
}

// OUTPUT: A A A A A
