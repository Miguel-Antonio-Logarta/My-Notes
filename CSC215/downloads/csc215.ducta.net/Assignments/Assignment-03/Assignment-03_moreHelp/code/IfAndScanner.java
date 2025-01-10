package asmt03_moreHelp;

import java.util.Scanner;

public class IfAndScanner {
    public static void main(String[] args) {

        /**************************
         * Scanner
         * - get the input
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your test score: ");
        double testScore = input.nextDouble();


        /**************************
         * if Statement
         * - process the input
         */
        double score = testScore; // Case: Test user's test score

        char letterGrade;

        if (score >= 90.0) {
            letterGrade = 'A';
        } else if (score >= 80.0) {
            letterGrade = 'B';
        } else if (score >= 70.0) {
            letterGrade = 'C';
        } else if (score >= 60.0) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }

        System.out.println("You scored a " + letterGrade + ".");

    }
}
/* OUTPUT
Please enter your test score: 76.5
You scored a C.

 */