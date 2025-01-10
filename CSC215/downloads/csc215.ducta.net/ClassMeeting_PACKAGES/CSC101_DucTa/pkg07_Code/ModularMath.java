package pkg07_Code;

import java.util.Scanner;

/**
 * @author ducta
 */

public class ModularMath {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an integer for dividend: ");
        int dividend = input.nextInt();

        System.out.println("|--------------------------------|");
        System.out.println("\t Modular Math --- ".toUpperCase());
        System.out.println("|--------------------------------|");
        System.out.println(" Idx.\tDividend\tModulo 5");
        int remainder;
        int divisor = 5;
        int decrement = 3;

        for (int i = 0; i < 5; i++) {
            remainder = dividend % divisor;
            System.out.println("  " + i + "\t\t   " + dividend + "\t\t   " + remainder);
            dividend -= decrement;
        }
        System.out.println("|--------------------------------|");

    }
}

/* OUTPUT
Please enter an integer for dividend: 17
|--------------------------------|
	 MODULAR MATH ---
|--------------------------------|
 Idx.	Dividend	Modulo 5
  0		   17		   2
  1		   14		   4
  2		   11		   1
  3		   8		   3
  4		   5		   0
|--------------------------------|

 */