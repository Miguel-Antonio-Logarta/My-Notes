package pkg03_Code;

/**
 * @author Duc Ta
 */

// Import class Scanner from package java.util
import java.util.Scanner;

public class UserQA {

    // Create a Scanner object 	
    static Scanner key = new Scanner(System.in);

    public static void main(String[] args) {

        // Prompt the user to enter name
        System.out.print("Tell me your name, please: \t");
        // Read a line and store it
        String a = key.nextLine();

        // Prompt the user to enter age
        System.out.print("Tell me your age, please: \t");
        // Read an int and store it
        int b = key.nextInt();

        // Prompt the user to enter height
        System.out.print("Tell me your height, please: \t");
        // Read a double and store it
        double c = key.nextDouble();

        // Display a blank line
        System.out.print("\n");

        // Display collected information
        System.out.println("Your name is: \t" + a + "\nYour age is: \t"
                + b + "\nYour height is: " + c);
    }
}
