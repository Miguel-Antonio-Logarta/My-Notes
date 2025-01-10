package pkg10_Code;

/**
 * @author Duc Ta
 */

public class MethodDemo {

    public static int sum(int i1, int i2) {
        int result = 0;
        for (int i = i1; i <= i2; i++) {
            result += i;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Sum from 1 to 10 is " + sum(1, 10));
        System.out.println("Sum from 20 to 37 is " + sum(20, 37));
        System.out.println("Sum from 35 to 49 is " + sum(35, 49));
    }
}
/* OUTPUT 
Sum from 1 to 10 is 55
Sum from 20 to 37 is 513
Sum from 35 to 49 is 630
*/