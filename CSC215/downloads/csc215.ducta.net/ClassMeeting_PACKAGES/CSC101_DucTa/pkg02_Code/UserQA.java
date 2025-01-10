package pkg02_Code;

import java.util.Scanner;

/**
 * @author Duc Ta Plus
 */
public class UserQA {

    static Scanner key = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Tell me your name please.");
        String a = key.nextLine();

        System.out.println("Tell me your age.");
        int b = key.nextInt();

        System.out.println("Tell me your height.");
        double c = key.nextDouble();

        System.out.println("Your name is " + a + ", your age is "
                + b + ", your height is " + c);
    }
}
