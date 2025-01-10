package pkg07_Code;

/**
 * @author ducta
 */

public class Loop_for_Sheep {

    public static void main(String[] args) {

        for (int count = 0; count < 5; count++) {
            System.out.println(count + " sheep");
            // count++ happens here
        }
        // System.out.println(count); // ERROR: Cannot find symbol


        int i;
        for (i = 0; i < 5; i++) {
            System.out.println(i + " sheep");
        }
        System.out.println(i); // 5

        for (int x = 0, y = 5; x < 5 && y > 0; x++, y--) {
        }

    }
}

/* OUTPUT
0 sheep
1 sheep
2 sheep
3 sheep
4 sheep
0 sheep
1 sheep
2 sheep
3 sheep
4 sheep
5

 */