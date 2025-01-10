package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MathMethods {

    public static void main(String[] args) {

        System.out.println(Math.ceil(2.1));    //   3.0
        System.out.println(Math.ceil(2.0));    //   2.0
        System.out.println(Math.ceil(-2.0));   // - 2.0
        System.out.println(Math.ceil(-2.1));   // - 2.0
        System.out.println(Math.floor(2.1));   //   2.0
        System.out.println(Math.floor(2.0));   //   2.0
        System.out.println(Math.floor(-2.0));  // – 2.0
        System.out.println(Math.floor(-2.1));  // - 3.0
        System.out.println(Math.rint(2.1));    //   2.0
        System.out.println(Math.rint(-2.0));   // – 2.0
        System.out.println(Math.rint(-2.1));   // - 2.0
        System.out.println(Math.rint(2.5));    //   2.0
        System.out.println(Math.rint(4.5));    //   4.0
        System.out.println(Math.rint(-2.5));   // - 2.0
        System.out.println(Math.round(2.6f));  //   3
        System.out.println(Math.round(2.0));   //   2
        System.out.println(Math.round(-2.0f)); // - 2
        System.out.println(Math.round(-2.6));  // - 3
        System.out.println(Math.round(-2.4));  // - 2

        System.out.println(Math.max(2, 3));    // 3
        System.out.println(Math.max(2.5, 3));  // 3.0
        System.out.println(Math.min(2.5, 4.6));// 2.5

        System.out.println(Math.abs(-2));      // 2
        System.out.println(Math.abs(-2.1));    // 2.1

        System.out.println(Math.pow(2, 3));    // 8.0
        System.out.println(Math.pow(3, 2));    // 9.0

        System.out.println(Math.sqrt(4));      // 2.0
        System.out.println(Math.sqrt(16));     // 4.0

        System.out.println(Math.sin(0));       // 0.0
        System.out.println(Math.cos(0));       // 1.0

        System.out.println((int) (Math.random() * 10)); // A radom integer between 0 and 9
        System.out.println(50 + (int) (Math.random() * 50)); // A radom integer between 50 and 99
    }
}
/* OUTPUT
3.0
2.0
-2.0
-2.0
2.0
2.0
-2.0
-3.0
2.0
-2.0
-2.0
2.0
4.0
-2.0
3
2
-2
-3
-2
3
3.0
2.5
2
2.1
8.0
9.0
2.0
4.0
0.0
1.0
3
50

 */