package pkg08_Code;

import java.util.Scanner;

/**
 * @author ducta
 */

public class ModularMath {

    public static void main(String[] args) {

        // Get keyboard input
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an integer for dividend: ");
        int dividend = input.nextInt();

        // Declare and initialize variables
        int remainder;
        int divisor = 5;
        int decrement = 3;

        // Formatting table elements 
        String tTittle = "Modular Math ---".toUpperCase(); // Title
        String hLine = "|";                               // Horizontal line
        for (int i = 0; i < 32; i++) {
            hLine += "-";
        }
        hLine += "|";

        // Printing table tittle and header
        System.out.println(hLine);                        // Horizontal line 1
        System.out.printf("%27s %n", tTittle);             // Title
        System.out.println(hLine);                        // Horizontal line 2
        System.out.printf("%7s %10s %10s %1d %n", "Idx.", "Dividend", "Modulo", divisor); // Header
        System.out.println(hLine);                        // Horizontal line 3

        // Printing table rows
        for (int i = 0; i < 5; i++) {
            remainder = dividend % divisor;
            System.out.printf("%5d %9d %11d %n", i, dividend, remainder);
            dividend -= decrement;
        }

        // Printing table bottom
        System.out.println(hLine);                        // Horizontal line 4
    }
}
/* OUTPUT
Please enter an integer for dividend: 17
|--------------------------------|
           MODULAR MATH ---
|--------------------------------|
   Idx.   Dividend     Modulo 5
|--------------------------------|
    0        17           2
    1        14           4
    2        11           1
    3         8           3
    4         5           0
|--------------------------------|

 */