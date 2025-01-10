/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg06_Code;

import java.util.Scanner;

public class Switch {

    public static void main(String[] args) {

        char grade = 0;         // 0 is ASCII code for Null character 
        String message = null;  // initialized vs. not initialized 

        Scanner input = new Scanner(System.in);

        while (grade != '!') {
            
            System.out.print("Please enter your grade(! to quit): ");
            grade = input.next().toUpperCase().charAt(0); // vs. nextLine()
            System.out.print("Your grade is " + grade + ". ");

            switch (grade) {    // char, byte, short, int ?
                case 'A':       // case data type?
                case 'B':
                case 'C':
                    message = "Good work! Keep it up.";
                    break;      // Optional. Fall through to 'F' (until 'break')
                case 'D':
             // case 'D':       // ERROR: Duplicate label
                case 'F':
                    message = "See your professor. Work harder.";
                    break;
                default:
                    message = "Invalid grade.";
            }

            System.out.println(message);
        }

        System.out.println("Thank you!");
    }
}
/* OUTPUT
Please enter your grade(! to quit): X
Your grade is X. Invalid grade.
Please enter your grade(! to quit): A
Your grade is A. Good work! Keep it up.
Please enter your grade(! to quit): b
Your grade is B. Good work! Keep it up.
Please enter your grade(! to quit): c
Your grade is C. Good work! Keep it up.
Please enter your grade(! to quit): D
Your grade is D. See your professor. Work harder.
Please enter your grade(! to quit): f
Your grade is F. See your professor. Work harder.
Please enter your grade(! to quit): !
Your grade is !. Invalid grade.
Thank you!
*/