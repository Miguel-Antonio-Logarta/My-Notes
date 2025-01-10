package pkg07_Code;

/**
 * @author Duc Ta PLUS
 */

public class OpsInForLoopPractice2_2 {

    public static void main(String[] args) {

        int i, n = 10, opTotal = 1, sum = 0;

        for (i = 0; i <= 2 * n; i++, opTotal += (i <= 2 * n + 1) ? 4 + 3 : 0) {

            sum += i + 3;
            System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 7));
        }

        System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 2 + 3));
    }
}

/* OUTPUT
i: 0	Total OPS: 8
i: 1	Total OPS: 15
i: 2	Total OPS: 22
i: 3	Total OPS: 29
i: 4	Total OPS: 36
i: 5	Total OPS: 43
i: 6	Total OPS: 50
i: 7	Total OPS: 57
i: 8	Total OPS: 64
i: 9	Total OPS: 71
i: 10	Total OPS: 78
i: 11	Total OPS: 85
i: 12	Total OPS: 92
i: 13	Total OPS: 99
i: 14	Total OPS: 106
i: 15	Total OPS: 113
i: 16	Total OPS: 120
i: 17	Total OPS: 127
i: 18	Total OPS: 134
i: 19	Total OPS: 141
i: 20	Total OPS: 148
i: 21	Total OPS: 153

 */