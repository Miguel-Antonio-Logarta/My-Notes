import java.util.Scanner;

public class CCValidator {
    public static final String VISA = "4";
    public static final String MASTER = "5";
    public static final String AMEX = "37";
    public static final String DISCOVERY = "6";

    /**
     * Checks if a string is numeric.
     * @return true if a string consists entirely of numbers. Can be prefixed by a negative sign (-)
     */
    public static boolean isNumeric(String numString) {
        try {
            Long.parseLong(numString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the credit card number has a valid credit card prefix
     * @param ccString A string containing a credit card number
     */
    public static boolean validPrefix(String ccString) {
        return ccString.startsWith(VISA) ||
                ccString.startsWith(MASTER) ||
                ccString.startsWith(AMEX) ||
                ccString.startsWith(DISCOVERY);
    }

    /**
     * Checks if a credit card number has a valid length. Valid credit card numbers have a length between 13
     * and 16 digits inclusive.
     * @param ccString A string containing a credit card number
     */
    public static boolean validLength(String ccString) {
        return ccString.length() >= 13 && ccString.length() <= 16;
    }

    /**
     * Checks if a credit card number is valid using a Luhn Check.
     * @param ccString A string containing a credit card number
     */
    public static boolean luhnCheck(String ccString) {
        char[] modifiedCCString = ccString.toCharArray();
        int evenPlacesSum = 0;
        int oddPlacesSum = 0;

        for (int i = 0; i < modifiedCCString.length; i++) {
            int currentDigit = ccString.charAt(ccString.length() - i - 1) - '0';
            // Get every second digit from right to left
            if (i % 2 == 1) {
                int doubled = currentDigit * 2;
                if (doubled >= 10) {
                    doubled = (doubled / 10) + (doubled % 10);

                }
                evenPlacesSum += doubled;
            } else {
                oddPlacesSum += currentDigit;
            }
        }

        return (evenPlacesSum + oddPlacesSum) % 10 == 0;
    }

    public static boolean performCCCheck(String ccString) {
        return
                isNumeric(ccString) &&
                validLength(ccString) &&
                validPrefix(ccString) &&
                luhnCheck(ccString);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ccNumber;
        System.out.print("Enter a credit card number: ");
        ccNumber = sc.nextLine();

        if (performCCCheck(ccNumber)) {
            System.out.println(ccNumber + " is a valid credit card number.");
        } else {
            System.out.println(ccNumber + " is not a valid credit card number.");
        }
    }
}

