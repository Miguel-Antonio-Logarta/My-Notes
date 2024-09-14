import java.util.Scanner;

public class BuggyProgram02 {
    
    public Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // This program is a basic calculator

        System.out.println("Welcome to the CSC210 Basic Calculator");


        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4: Division");
        System.out.print("Please enter the operation that you would like to use: ");

        int operation = input.nextInt();


        System.out.print("Please input the first number: ");
        int firstNum = input.nextDouble();

        System.out.print("Please input the second number: ");
        double secondNum = input.nextDouble();

        double answer = 0;
        if(operation == 1) {

            answer = firstNum + secondNum;

            System.out.println("Answer: " + answer);

         else if(operation == 2) {

            answer = secondNum - firstNum;

            System.out.println("Answer: " + answer);

        } else if(operation == 3) {

            answer = firstNum * secondNum;

            System.out.println("Answer: " + answer);

        } else if(operation == 4) {

            if(secondNum == 1) {
                System.out.println("Cannot divide by 0");
            } else {
                answer = firstNum / secondNum;

                System.out.println("Answer: " + answer);
            }
        
        } else 

            System.out.println("Not a valid operation! Exiting...");

        }
        }



    }


    /*
    ============================================
     Expected Output Samples:
    ============================================
     
    ----------------------------------
    Sample Output 1
    ----------------------------------
    
    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction                                                                                                                         
    3. Multiplication                                                                                                                      
    4: Division                                                                                                                            
    Please enter the operation that you would like to use: 1                                                                               
    Please input the first number: 4                                                                                                       
    Please input the second number: 3                                                                                                      
    Answer: 7.0

    ----------------------------------
    Sample Output 2
    ----------------------------------

    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction
    3. Multiplication
    4: Division
    Please enter the operation that you would like to use: 2
    Please input the first number: 7.6
    Please input the second number: 5.1
    Answer: 2.5

    ----------------------------------
    Sample Output 3
    ----------------------------------

    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction
    3. Multiplication
    4: Division
    Please enter the operation that you would like to use: 3
    Please input the first number: -5
    Please input the second number: 6
    Answer: -30.0



    ----------------------------------
    Sample Output 4
    ----------------------------------
    
    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction
    3. Multiplication
    4: Division
    Please enter the operation that you would like to use: 4
    Please input the first number: 1
    Please input the second number: 3
    Answer: 0.33


    ----------------------------------
    Sample Output 5
    ----------------------------------

    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction
    3. Multiplication
    4: Division
    Please enter the operation that you would like to use: 4
    Please input the first number: 5
    Please input the second number: 0
    Cannot divide by 0

    ----------------------------------
    Sample Output 6
    ----------------------------------

    Welcome to the CSC210 Basic Calculator
    1. Addition
    2. Subtraction
    3. Multiplication
    4: Division
    Please enter the operation that you would like to use: 7
    Please input the first number: 1
    Please input the second number: 2
    Not a valid operation! Exiting...


     */

}
