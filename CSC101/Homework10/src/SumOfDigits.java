import java.util.Scanner;

public class SumOfDigits {
    private static final int UPPER_BOUND = 999;
    private static final int LOWER_BOUND = 0;

    /**
     * Acquires an integer from user input
     *
     * @param low  an unsigned <code>int</code> that specifies the lower bound
     * @param high an unsigned <code>int</code> that specifies the upper bound
     * @return an <code>int</code> that holds the number entered by the user. Else, it returns -1 if the parameters
     * or the user input are invalid (out of bounds).
     */
    public static int getUserInput(int low, int high) {
        if (low < 0 || high < 0) {
            return -1;
        }

        Scanner sc = new Scanner(System.in);
        int threeDigit = sc.nextInt();

        if (threeDigit < low || threeDigit > high) {
            return -1;
        }

        return threeDigit;
    }

    /**
     * Acquires the sum of each individual number from the provided digits
     *
     * @param digits digits to provide
     * @return the sum of each number from <code>digits</code>
     */
    public static int getSum(int digits) {
        int sum = 0;
        int divisor = 1;

        while (digits / divisor != 0) {
            sum += (digits / divisor) % 10;
            divisor = divisor * 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        int threeDigits;
        int sum;

        System.out.printf("Enter an integer (%d,%d) and I will compute the sum of digits: ", LOWER_BOUND, UPPER_BOUND);
        threeDigits = getUserInput(0, 999);

        if (threeDigits >= 0) {
            sum = getSum(threeDigits);
            System.out.printf("The sum of digits of %d is: %d", threeDigits, sum);
        } else {
            System.out.println("The value you entered is invalid");
            System.out.printf("Please only enter a value between %d and %d inclusive", LOWER_BOUND, UPPER_BOUND);
        }
    }
}