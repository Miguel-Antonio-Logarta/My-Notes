/****************************************************************
 *
 * File: BuggyProgram01_Fixed.java
 * By:   Miguel Antonio Logarta
 * Date: 09-04-2024
 *
 * Description: This is a fix for buggy program 01. It is an
 * automatic grading calculator. It assigns letter grades
 * based on the student's percentage.
 *
 ****************************************************************/

import java.util.Scanner;

public class BuggyProgram01_Fixed {


    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // This program is a letter grade calculator

        System.out.print("Student First Name: ");
        String firstName = input.nextLine();

        System.out.print("Student Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Student's Grade: ");
        float percentage = input.nextFloat();

        String letterGrade;

        if (percentage >= 90.0) {
            letterGrade = "A";
        } else if (percentage >= 80.0) {
            letterGrade = "B";
        } else if (percentage >= 70.0) {
            letterGrade = "C";
        } else if (percentage >= 60.0) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }


        System.out.printf("%s %s received an %s", firstName, lastName, letterGrade);

    }

    /*
    ============================================
     Expected Output Samples:
    ============================================
     
    ----------------------------------
    Sample Output 1
    ----------------------------------
    Student First Name: Minnie
    Student Last Name: Mouse
    Student's Grade: 92                                                                                                                    
    Minnie Mouse received an A

    ----------------------------------
    Sample Output 2
    ----------------------------------
    Student First Name: Boss
    Student Last Name: Baby
    Student's Grade: 87
    Boss Baby received an B

    ----------------------------------
    Sample Output 3
    ----------------------------------
    Student First Name: Baymax
    Student Last Name: Hamada
    Student's Grade: 75
    Baymax Hamada received an C

    ----------------------------------
    Sample Output 4
    ----------------------------------
    Student First Name: Winnie
    Student Last Name: Pooh
    Student's Grade: 63
    Winnie Pooh received an D


    ----------------------------------
    Sample Output 5
    ----------------------------------
    Student First Name: Otto
    Student Last Name: Minion
    Student's Grade: 23
    Otto Minion received an F


    */
}