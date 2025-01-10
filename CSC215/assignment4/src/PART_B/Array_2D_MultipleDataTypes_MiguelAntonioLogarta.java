package PART_B;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Array_2D_MultipleDataTypes_MiguelAntonioLogarta {

    static Integer[] getIntegers(int n) {
        Integer[] values = new Integer[n];

        while (true) {
            Scanner sc = new Scanner(System.in);

            try {
                for (int i = 0; i < n; i++) {
                    int input;
                    input = sc.nextInt();
                    values[i] = input;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.printf("Your input was invalid. Please enter %d integer(s)\n", n);
                System.out.printf("Please enter %d Integers: ", n);
            }
        }

        return values;
    };

    static Character[] getCharacters(int n) {
        Character[] values = new Character[n];

        while (true) {
            Scanner sc = new Scanner(System.in);

            try {
                for (int i = 0; i < n; i++) {
                    char extractedChar;
                    String input;
                    input = sc.next();

                    if (input.trim().length() > 1) {
                        throw new InputMismatchException();
                    } else {
                        extractedChar = input.charAt(0);
                        values[i] = extractedChar;
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.printf("Your input was invalid. Please enter %d Character(s)\n", n);
                System.out.printf("Please enter %d Characters: ", n);
            }
        }

        return values;
    };

    static String[] getString(int n) {
        String[] values = new String[n];

        while (true) {
            Scanner sc = new Scanner(System.in);

            try {
                for (int i = 0; i < n; i++) {
                    String input;
                    input = sc.next().trim();
                    if (input.isEmpty()) {
                        throw new InputMismatchException();
                    }
                    values[i] = input;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.printf("Your input was invalid. Please enter %d Strings(s)\n", n);
                System.out.printf("Please enter %d Strings: ", n);
            }
        }

        return values;
    };

    static Object[] getIntCharString() {
        Object[] values = new Object[3];

        while (true) {
            Scanner sc = new Scanner(System.in);

            try {
                int intInput;
                String charInput;
                char extractedCharInput;
                String strInput;

                intInput = sc.nextInt();

                charInput = sc.next().trim();
                if (charInput.length() > 1) {
                    throw new InputMismatchException();
                }
                extractedCharInput = charInput.charAt(0);

                strInput = sc.next().trim();
                if (strInput.isEmpty()) {
                    throw new InputMismatchException();
                }

                values[0] = intInput;
                values[1] = extractedCharInput;
                values[2] = strInput;

                return values;
            } catch (InputMismatchException e) {
                System.out.print("Your input was invalid. Please enter 1 Int, 1 Char, 1 String\n");
                System.out.print("Please enter 1 Int, 1 Char, 1 String: ");
            }
        }
    }

    public static void printDataTypes(Object[][] arr, int leftMargin, int columnWidth) {
        for (Object[] row : arr) {
            System.out.print(" ".repeat(leftMargin));
            for (Object obj : row) {
                String objType = obj.getClass().getSimpleName();
                System.out.printf("%s" + " ".repeat(columnWidth - objType.length()) + " ", objType);
            }
            System.out.println();
        }
    }

    public static void printDataValues(Object[][] arr, int leftMargin, int columnWidth) {
        for (Object[] row : arr) {
            System.out.print(" ".repeat(leftMargin));
            for (Object obj : row) {
                String objString = obj.toString();
                System.out.printf("%s" + " ".repeat(columnWidth - objString.length()) + " ", objString);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Object[][] inputDatabase = new Object[4][3];

        // All the inputs discard the last token
        System.out.print("Row 1 | Please enter 3 Integers: ");
        inputDatabase[0] = getIntegers(3);

        System.out.print("Row 2 | Please enter 3 Characters: ");
        inputDatabase[1] = getCharacters(3);

        System.out.print("Row 3 | Please enter 3 Strings: ");
        inputDatabase[2] = getString(3);

        System.out.print("Row 4 | 1 Int, 1 Char, 1 String: ");
        inputDatabase[3] = getIntCharString();

        // Show data types and values
        System.out.println();
        System.out.println("Your 2D array of multiple data types: ");
        System.out.println();

        System.out.println("- Data Type View:");
        printDataTypes(inputDatabase, 15, 30);
        System.out.println();

        System.out.println("- Data Value View:");
        printDataValues(inputDatabase, 15, 30);

    }
}
