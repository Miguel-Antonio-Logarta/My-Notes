package pkg07_Code;

/**
 * @author Duc Ta PLUS
 */


public class OpsInForLoopPractice2_1 {

    public static void main(String[] args) {

        int i, n = 10, opTotal = 1, sum = 0;

        for (i = 0; i <= 2 * n; i++, opTotal += (i <= 2 * n + 1) ? 3 + 3 : 0) {
            sum += i + 3;
            System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 6));
        }

        System.out.println("i: " + i + "\tTotal OPS: " + (opTotal + 1 + 3));
    }
}

/* OUTPUT
i: 0	Total OPS: 7
i: 1	Total OPS: 13
i: 2	Total OPS: 19
i: 3	Total OPS: 25
i: 4	Total OPS: 31
i: 5	Total OPS: 37
i: 6	Total OPS: 43
i: 7	Total OPS: 49
i: 8	Total OPS: 55
i: 9	Total OPS: 61
i: 10	Total OPS: 67
i: 11	Total OPS: 73
i: 12	Total OPS: 79
i: 13	Total OPS: 85
i: 14	Total OPS: 91
i: 15	Total OPS: 97
i: 16	Total OPS: 103
i: 17	Total OPS: 109
i: 18	Total OPS: 115
i: 19	Total OPS: 121
i: 20	Total OPS: 127
i: 21	Total OPS: 131

 */