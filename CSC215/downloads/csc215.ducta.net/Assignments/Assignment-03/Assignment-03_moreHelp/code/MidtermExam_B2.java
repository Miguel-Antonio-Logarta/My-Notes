package asmt03_moreHelp;

/**
 * @author Duc Ta
 */

import java.util.Scanner;

public class MidtermExam_B2 {

    public static void main(String[] args) {

        Scanner key = new Scanner(System.in);

        System.out.print("- Your 1st Programming Course: ");
        String courseName = key.nextLine();

        System.out.print("- Hours programming/week: ");
        float hoursPerWeek = key.nextFloat();

        // Assumption: Programming should every day (7 days per week)
        float hoursPerDay = hoursPerWeek / 7;

        String msg = (hoursPerDay > 3) ? "Keep it up!" : "Do you have time to succeed?";

        System.out.println("");
        System.out.println("- Your Course: " + courseName);
        System.out.printf("- Hours programming/day: %.2f%n", hoursPerDay);
        System.out.println("- " + msg);
    }
}
/* SAMPLE RUN
- Your 1st Programming Course: CSC 210
- Hours programming/week: 12.5

- Your Course: CSC 210
- Hours programming/day: 1.79
- Do you have time to succeed?
*/

/*SAMPLE RUN
- Your 1st Programming Course: CSC 210
- Hours programming/week: 31.5

- Your Course: CSC 210
- Hours programming/day: 4.50
- Keep it up!
*/