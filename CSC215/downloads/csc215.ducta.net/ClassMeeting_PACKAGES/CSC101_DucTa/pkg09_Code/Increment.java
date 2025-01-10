package pkg09_Code;

/**
 * @author Duc Ta
 */

public class Increment {

    public static void main(String[] args) {
        int x = 1;
        System.out.println("Before the call, x is " + x);
        increment(x);
        System.out.println("After the call, x is " + x);
    }

    public static void increment(int n) {
        n++;
        System.out.println("n inside the method is " + n);
    }
}

/*OUTPUT 
Before the call, x is 1
n inside the method is 2
After the call, x is 1
*/
