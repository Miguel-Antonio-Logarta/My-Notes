package pkg06_Code;
/**
 * @author Duc Ta
 */

public class DecimalValue {
    public static void main(String[] args) {
        char one = '1';
        System.out.println(one);

        System.out.println((int)'a' - (int)'A');        // not recommended
        System.out.println((int)('a') - (int)('A'));    // recommended
    }
}

/* OUTPUT
1
32
32

 */
