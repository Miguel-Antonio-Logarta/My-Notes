/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg06_Code;

/**
 *
 * @author Duc Ta
 */

public class StringDemo {

    public static void main(String[] args) {
        System.out.println(" Hello World from SFSU! "); // Notice whitespaces
        // OUTPUT: Hello World from SFSU!

        String message = " Hello World from SFSU! ";    // Notice whitespaces
        System.out.println(message);
        // OUTPUT: Hello World from SFSU!
        System.out.println(message); // Reusability
        // OUTPUT: Hello World from SFSU!

        System.out.println(message.length());        // 24
        System.out.println(message.trim().length()); // 22
        System.out.println(message.length());        // 24

        System.out.println(message.charAt(7));       // W
        System.out.println(message.concat("--- CSC 210"));
        // OUTPUT:  Hello World from SFSU! --- CSC 210
    }
}

/* OUTPUT
 Hello World from SFSU!
 Hello World from SFSU!
 Hello World from SFSU!
24
22
24
W
 Hello World from SFSU! --- CSC 210

 */