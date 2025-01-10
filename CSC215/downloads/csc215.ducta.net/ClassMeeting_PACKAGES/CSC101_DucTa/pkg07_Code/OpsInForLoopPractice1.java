package pkg07_Code;

/**
 * @author Duc Ta PLUS
 */

public class OpsInForLoopPractice1 {

    public static void main(String[] args) {

        int i, n = 10, opTotal = 1; // 2
        int sum = 0;                // 1

        for (i = 0; i <= n; i++, opTotal += (i <= n + 1) ? 3 + 2 : 0) {
            sum += i;
            System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 5));
        }

        System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 1 + 2 + 1));

    }
}

/* OUTPUT
i: 0	Total OPS: 6
i: 1	Total OPS: 11
i: 2	Total OPS: 16
i: 3	Total OPS: 21
i: 4	Total OPS: 26
i: 5	Total OPS: 31
i: 6	Total OPS: 36
i: 7	Total OPS: 41
i: 8	Total OPS: 46
i: 9	Total OPS: 51
i: 10	Total OPS: 56
i: 11	Total OPS: 60

 */