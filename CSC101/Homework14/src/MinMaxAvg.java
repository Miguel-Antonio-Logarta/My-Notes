import java.util.Scanner;

public class MinMaxAvg {
    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        double average = 0;
        int numbersEntered = 0;
        int userInt = -1;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter any amount of numbers with spaces between them. The last number should be a zero (0):");
        userInt = sc.nextInt();

        while (userInt != 0) {
            if (userInt < min) {
                min = userInt;
            } else if (userInt > max) {
                max = userInt;
            }
            sum += userInt;
            numbersEntered++;
            userInt = sc.nextInt();
        }
        average = (double)sum / (double)numbersEntered;
        System.out.println("The max: " + max);
        System.out.println("The min: " + min);
        System.out.println("The avg: " + average);
    }
}
