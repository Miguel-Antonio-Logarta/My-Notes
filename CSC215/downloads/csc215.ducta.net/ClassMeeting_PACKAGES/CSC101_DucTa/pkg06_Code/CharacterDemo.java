/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg06_Code;

/**
 * @author Duc Ta
 */

public class CharacterDemo {

    public static void main(String args[]) {

        char c1 = '5';
        if (Character.isDigit(c1)) {
            System.out.println("Yes. A digit."); // Yes. A digit.
        }
        // Also try isLetter, isLetterOrDigit, isLowerCase, isUpperCase

        char c2 = 'G';
        System.out.println(Character.toLowerCase(c2)); // g
        System.out.println(c2);                        // G
        // Also try toUpperCase
        // for more: https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/lang/Character.html
    }
}

/* OUTPUT
Yes. A digit.
g
G

 */