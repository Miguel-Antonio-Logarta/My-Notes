package pkg04_Code;

/**
 * @author Duc Ta
 */

public class Operator_Assignment_ChainedAssignment {
    public static void main(String[] args) {
        // int a = b = c = d;       // err
        // int a = b = c = d = 1;   // err

        int a, b, c, d;
        // a = b; // err
        a = b = c = d = 1;
        System.out.println(a + " " + b + " " + c + " " + d);
    }
}
/* OUTPUT
1 1 1 1
 */