package asmt03_moreHelp;

/**
 * @author Duc Ta
 */

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class MidtermExam_C1_A {

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        printOutput(getCourseName(key), calculateNumHoursDaily(getNumHoursWeekly(key)));
    }

    private static void printOutput(String courseName, float numHoursDaily) {

        System.out.print("\n- Your gym: " + courseName);
        System.out.printf("\n- Your visits/month: %.2f", numHoursDaily);
        System.out.println("\n- " + determineMessage(numHoursDaily));
    }

    private static String getCourseName(Scanner key) {
        System.out.print("- Your 1st Programming Course: ");
        return key.nextLine();
    }

    private static float getNumHoursWeekly(Scanner key) {
        System.out.print("- Hours programming/week: ");
        return key.nextFloat();
    }

    private static float calculateNumHoursDaily(float numHoursWeekly) {
        return numHoursWeekly / 7;
    }

    private static String determineMessage(float numHoursDaily) {
        return (numHoursDaily > 3) ? "Keep it up!" : "Do you have time to succeed?";
    }
}
/* SAMPLE RUN
- Your 1st Programming Course: CSC 210
- Hours programming/week: 12.5

- Your gym: CSC 210
- Your visits/month: 1.79
- Do you have time to succeed?
 */