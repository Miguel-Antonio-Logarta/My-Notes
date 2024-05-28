import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int winningNumber = rand.nextInt(1, 100);
        int answer = -1;

        System.out.println("Welcome to the number guessing game!");
//        System.out.println("winningNumber = " + winningNumber);
        while (true) {
            System.out.print("Guess a number between 1 to 99: ");
            answer = sc.nextInt();

            if (answer > winningNumber) {
                System.out.println("Your guess is too high!");
            } else if (answer < winningNumber) {
                System.out.println("Your guess is too low!");
            } else {
                System.out.println("Yes the number is " + winningNumber + "!");
                break;
            }
        }
    }
}
