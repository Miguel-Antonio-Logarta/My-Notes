package pkg07_Code;

/**
 * @author ducta
 */

public class Loop_for {

    public static void main(String[] args) {

        for (int count = 0; count < 5; count++) {
            System.out.println(count + ". CSC210!");
            // count++ happens here
        }
        // System.out.println(count); // ERROR: Cannot find symbol


        int i;
        for (i = 0; i < 5; i++) {
            System.out.println(i + ". CSC210!");
        }
        System.out.println(i); // 5

        for (int x = 0, y = 5; x < 5 && y > 0; x++, y--) {
        }

    }
}

/* OUTPUT
0. CSC210!
1. CSC210!
2. CSC210!
3. CSC210!
4. CSC210!
0. CSC210!
1. CSC210!
2. CSC210!
3. CSC210!
4. CSC210!
5

 */