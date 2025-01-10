/*
 * BMI table will have two modes upon initialization (imperial mode and english mode)
 * The strategy will instantiate a table and set the mode
 * After getting the user input of course.
 *
 * Now I don't know if we should use a dependency injection for the BMI table. As in
 * we will inject a dependency to the context handler.
 * */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BMI_CSC215_Master_MiguelAntonioLogarta {
    public static void selectProgram() {
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
                runProgram(new BMIEnglish());
            } else if (userInput.matches("(?i)\\bm+e+t+r+i+c+\\b")) {
                runProgram(new BMIMetric());
            } else if (userInput.equals("!")) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Your input was not a valid value");
            }
        }
    }

//    public static void runProgram() {
//        int choice = selectProgram();
//        BMIContext context = new BMIContext();
//
//        if (choice == 1) {
//            context.setBMIStrategy(new EnglishConcreteStrategy());
//        } else if (choice == 2) {
//            context.setBMIStrategy(new MetricConcreteStrategy());
//        } else {
//            System.out.println("Goodbye!");
//        }
//
//
//
////        String name;
////        int height;
////        double weight;
////        double lowWeight;
////        double highWeight;
////        double BMI;
////        String BMICategory;
////        BMITable bmiTable = new BMITable();
////
////
////        name = BMICommon.getName();
////        height = BMICommon.getHeightMetric(name);
////        weight = BMICommon.getWeight(name,
////                String.format("Please enter weight in kilograms for %s: ", name),
////                "Your input was invalid. Please enter a number (decimals allowed)"
////        );
////        BMI = BMICommon.calculateBMIMetric(height, weight);
////        BMICategory = BMICommon.getBMICategory(BMI);
////
////        BMICommon.printSummary(name, BMI, BMICategory);
////
////        lowWeight = BMICommon.getWeight(name,
////                String.format("Please enter a LOW weight in kilograms for %s: ", name),
////                "Your input was invalid. Please enter a number (decimals allowed)"
////        );
////        highWeight = BMICommon.getWeight(name,
////                String.format("Please enter a HIGH weight in kilograms for %s: ", name),
////                "Your input was invalid. Please enter a number (decimals allowed)"
////        );
////
////        bmiTable.setTargetBMI(height, weight);
////        bmiTable.generateTable(lowWeight, highWeight, 25);
////        bmiTable.renderTable();
////
////        BMICommon.printProgramFooter(name);
//    }
    public static void runProgram(BMIProgram program) {

    }

    public static void main(String[] args) {
        selectProgram();

        // Create classes

        /*
         * Main Class
         * Client / BMI Program Class
         * English Class
         * Metric Class
         * BMI Class
         * BMI Table
         * Table Class
         * */
    }
}

