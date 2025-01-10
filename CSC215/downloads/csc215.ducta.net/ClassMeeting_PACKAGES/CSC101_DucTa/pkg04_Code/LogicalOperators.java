package pkg04_Code;

/**
 * @author Duc Ta
 */

public class LogicalOperators {
    public static void main(String[] args) {

        if (2 > 1 && 2 < 3) {
            System.out.println("Good morning!");    // Good morning
        }

        if (2 > 1 || 2 > 3) {
            System.out.println("Good morning!");    // Good morning
        }

        if (!(2 < 1)) {
            System.out.println("Good morning!");    // Good morning
        }

        if ( ( 2 > 1) ^ ( 2 < 1) ) {
            System.out.println("Good morning!");    // Good morning
        }

        if ( ( 2 > 1) != ( 2 < 1) ) {
            System.out.println("Good morning!");    // Good morning
        }
    }
}

/* OUTPUT
Good morning!
Good morning!
Good morning!
Good morning!
Good morning!

 */