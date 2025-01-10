package pkg07_Code;

/**
 * @author ducta
 */

public class Loop_do_while_Sheep {

    public static void main(String[] args) {

        int count = 0;
        do {
            System.out.println(count + " sheep");
            count++;
        } while (count < 5);

        System.out.println(count); // 5

    }
}

/* OUTPUT
0 sheep
1 sheep
2 sheep
3 sheep
4 sheep
5

 */