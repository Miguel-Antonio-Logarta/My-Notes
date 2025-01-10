/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05_Code;

/**
 * @author Duc Ta
 */

public class Conversions {

    public static void main(String[] args) {

        // Integer to floating point automatically
        System.out.println(7 * 11.0);    // 77.0
        System.out.println(7.0 * 11.0);  // 77.0

        // Casting in parentheses, double into int 
        System.out.println((int) 101.3);  // 101

        // int, floor 
        System.out.println(7 / 11);       // 0
        System.out.println(11 / 7);       // 1

        // casted to float
        System.out.println((float) 7 / 11); // 0.6363636
        System.out.println((float) 11 / 7); // 1.5714285

        // casted to double
        System.out.println((double) 7 / 11); // 0.6363636363636364
        System.out.println((double) 11 / 7); // 1.5714285714285714
        System.out.format("Format %8f%n", (double) 11 / 7); // Format 1.571429

        // casted to double
        System.out.println((double) (7 / 11)); // 0.0
        System.out.println((double) (11 / 7)); // 1.0
        System.out.format("Format %8f%n", (double) (11 / 7)); // Format 1.000000
    }
}

/* OUTPUT
77.0
77.0
101
0
1
0.6363636
1.5714285
0.6363636363636364
1.5714285714285714
Format 1.571429
0.0
1.0
Format 1.000000
 */