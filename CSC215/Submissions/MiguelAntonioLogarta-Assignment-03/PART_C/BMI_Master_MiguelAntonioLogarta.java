import java.util.Scanner;

public class BMI_Master_MiguelAntonioLogarta {
    public static int selectProgram() {
        Scanner sc = new Scanner(System.in);

        System.out.println("My CSC 215 BMI Calculator Projects");
        System.out.println("    1. BMI, English");
        System.out.println("    2. BMI, Metric");

        System.out.println("[ USER MANUAL ] Enter an exclamation mark ! to end.");

        while (true) {
            System.out.print("Please enter the version you want to try: ");
            String userInput = sc.nextLine();

            /* *
            * Regex does a case-insensitive match where each character in 'English' or 'Metric' can be
            * repeated one or more times.
            * /english/ matches any substring containing "english"
            * /\\benglish\\b/ matches "english". \\b means No words in before or after "english".
            * /\\b(?i)english\\b/ matches "english" and "English". (?i) means case-insensitive search
            * /\\b(?i)\be+n+g+l+i+s+h+\\b/ matches "ennnggliiish". (+) Allows characters to repeat 1 or more times.
            * */
            if (userInput.matches("(?i)\\be+n+g+l+i+s+h+\\b")) {
                return 1;
            } else if (userInput.matches("(?i)\\bm+e+t+r+i+c+\\b")) {
                return 2;
            } else if (userInput.equals("!")) {
                return -1;
            } else {
                System.out.println("Your input was not a valid value");
            }
        }
    }

    public static void runProgram() {
        int choice = selectProgram();
        if (choice == 1) {

        } else if (choice == 2) {
            BMI_CSC215_Metric_MiguelLogarta.runBMIMetric();
        } else {
            System.out.println("Goodbye!");
        }
    }

    public static void main(String[] args) {
        runProgram();
    }
}
