package pkg12_Code;

/**
 * @author Duc Ta
 */

public class SaySomething {

    public static void main(String[] args) {
        // Method basic
        saySomething("Midterm Exam!");

        // Methods because they are reusable
        saySomething("Let's ace it!");

        // Methods and loops
        System.out.println("\nAgain, in loop!");
        for (int controlVariable = 0; controlVariable < 3; controlVariable++) {
            saySomething("Value of controlVariable: " + controlVariable);
        }

        // Methods, loops, and 1D arrays. Shorthand notation / the array initializer.
        String[] counts = {"One", "Two", "Three"};
        for (int index = 0; index < counts.length; index++) {
            saySomething(counts[index]);
        }

        // Methods, loops, and 1D arrays. Declare, create, and initalize. 
        String[] messages = new String[3];
        messages[0] = "Hi";
        messages[1] = "Howdy";
        messages[2] = "Bye";

        for (int index = 0; index < messages.length; index++) {
            saySomething(messages[index]);
        }
    }

    // A void method that says a message
    private static void saySomething(String message) {
        System.out.println(message);
    }
}

/* OUTPUT
Midterm Exam!
Let's ace it!

Again, in loop!
Value of controlVariable: 0
Value of controlVariable: 1
Value of controlVariable: 2
One
Two
Three
Hi
Howdy
Bye

 */