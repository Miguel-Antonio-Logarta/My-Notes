package pkg07_Code;

/**
 * @author Duc Ta PLUS
 */

public class OpsInForLoopPractice2_3 {

    public static void main(String[] args) {

        int i, n = 10, opTotal = 1, sum = 0;

        for (i = 0; i <= 2 * n; i++, opTotal += (i <= 2 * n + 1) ? 4 + 4 : 0) {
            n--;
            sum += i + 3;
            System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 8));
        }

        System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 2 + 3));
    }
}

/* OUTPUT
i: 0	Total OPS: 9
i: 1	Total OPS: 17
i: 2	Total OPS: 25
i: 3	Total OPS: 33
i: 4	Total OPS: 41
i: 5	Total OPS: 49
i: 6	Total OPS: 57
i: 7	Total OPS: 62

 */