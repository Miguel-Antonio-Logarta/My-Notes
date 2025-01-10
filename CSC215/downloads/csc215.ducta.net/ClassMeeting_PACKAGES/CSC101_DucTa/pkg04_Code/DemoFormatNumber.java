/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg04_Code;

import java.text.DecimalFormat;
import java.util.Calendar;      // Date and Time
import java.util.Locale;

/**
 *
 * @author Duc Ta
 */

public class DemoFormatNumber {

    public static void main(String[] args) {

        // 16 D and 8 F
        System.out.println("\n--- Observations ---");
        System.out.println(1.0 / 3.0);      // 0.3333333333333333
        System.out.println(1.0F / 3.0F);    // 0.33333334

        // printf and format
        System.out.println("\n--- Observations ---");
        double d;
        d = 1.0 / 3.0;
        System.out.println(100.0 / 3.0);    // 33.333333333333336
        System.out.format("%028.24f%n", d); // 000.333333333333333300000000
        d = 1000.0 / 3.0;
        System.out.format("%028.24f%n", d); // 333.333333333333300000000000
           
        // more numeric formatting
        System.out.println("\n--- Observations ---");
        long n = 461012;
        System.out.format("%d%n", n);      // 461012     // %d A decimal integer, %n new line
        System.out.format("%08d%n", n);    // 00461012   // %08 Eight characters in width, with leading zeroes as necessary
        System.out.format("%+8d%n", n);    //  +461012   // %+8 Include sign, whether positive or negative
        System.out.format("%,8d%n", n);    //  461,012   // %,  Include locale-specific grouping characters
        System.out.format("%-,8d%n", n);   // 461,012    // %-  Left-justified
        System.out.format("%+,8d%n%n", n); // +461,012   // Combo

        double pi = Math.PI;

        System.out.format("%f%n", pi);      // 3.141593   // %f a float
        System.out.format("%.3f%n", pi);    // 3.142      // %.3 three places after decimal point
        System.out.format("%10.3f%n", pi);  //      3.142 // %10.3 Ten characters in width, right justified, with three places after the decimal point
        System.out.format("%-10.3f%n", pi); // 3.142      // %-10.3 Left-justified

        System.out.format(Locale.FRANCE, "%-10.4f%n%n", pi); // 3,1416 // % Locale

        Calendar c = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", c, c, c); // September 4, 2019  // Local full name of month
        System.out.format("%tl:%tM %tp%n", c, c, c);  // 2:34 am            // Time
        System.out.format("%tD%n", c);                // 05/29/06           // Simple data

        // Custom format
        System.out.println("\n--- Observations ---");
        customFormat("###,###.###", 123456.789);  // --> 123,456.789
        customFormat("###.##", 123456.789);       // --> 123456.79
        customFormat("000000.000", 123.78);       // --> 000123.780
        customFormat("$###,###.###", 12345.67);   // --> $12,345.67
    }

    static public void customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(output);
    }
}

/* OUTPUT
--- Observations ---
0.3333333333333333
0.33333334

--- Observations ---
33.333333333333336
000.333333333333333300000000
333.333333333333300000000000

--- Observations ---
461012
00461012
 +461012
 461,012
461,012
+461,012

3.141593
3.142
     3.142
3.142
3,1416

February 23, 2023
8:59 am
02/23/23

--- Observations ---
123,456.789
123456.79
000123.780
$12,345.67

 */