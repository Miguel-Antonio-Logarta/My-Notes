package pkg07_Code;

/**
 * @author Duc Ta PLUS
 */

/**
 * Count the number of operations in 
 * the control structure of the for-loop
 *
 * * * ORDER: 
 * * 1. i = 0   happens ONCE
 * * 2. i < n   happens every time
 * * 3. EXE     for-loop body statements execute only when step 2 is satisfied 
 * * 4. i++     happens only when step 3 happens
 *
 * for (int i = 0;        i < n;                 i++ ) 
 * i    assignment     comparison      assignment & addition
 * 0        1               1               1           1 
 * 1        0               1               1           1
 * 2        0               1               1           1 
 * 3        0               1               1           1
 * ...
 * n        0               1               0           0
 *
 * Total: 1 + (n + 1) + 2n = 3n + 2
 */

// Example:
//          Count all operations
//          int i, n = 10, sum = 0;
//          for (i = 0; i <= 2n; i++) {
//               sum += i + 3;
//          }

public class OpsInForLoopPractice {

    public static void main(String[] args) {

        int i, n = 10, sum = 0; // A, 2 assignments, 2 operations, once

        // B: When for-loop starts, i = 0. 1 assignment, 1 operation, once only
        // C: i <= 2*n. 1 multiplication and 1 comparison, 2 operations, once each loop
        // D: sum = sum + i + 3. 1 assignment and 2 additions, 3 operations, once each loop
        // E: i = i + 1. 1 addition and 1 assignment, 2 operations, once each loop
        int opTotal = 1;    // B. Counter not included.

        // 
        for (i = 0; i <= 2 * n; i++, opTotal += 2) { // E
            sum += i + 3;     // D
            opTotal += 2 + 3; // C and D. Counter not included.
            System.out.println("i: " + i + "\tTotal OPS: " + opTotal);
        }

        // F: When i = 2n +1, +1 multiplication operation and +1 comparison. 
        opTotal += 2 + 2; // A and F. Counter not included.
        System.out.println("i: " + i + "\tTotal OPS: " + opTotal);

        // 1 + (2n + 2)*2 + (2n + 1)*2 = 8n + 7
        //                  (2n + 1)*3 = 6n + 3
        //                          +2 = 2
        //                             = 14n + 12
        // if n == 10, sum = 152
    }
}
/*
i: 0	Total OPS: 6
i: 1	Total OPS: 13
i: 2	Total OPS: 20
i: 3	Total OPS: 27
i: 4	Total OPS: 34
i: 5	Total OPS: 41
i: 6	Total OPS: 48
i: 7	Total OPS: 55
i: 8	Total OPS: 62
i: 9	Total OPS: 69
i: 10	Total OPS: 76
i: 11	Total OPS: 83
i: 12	Total OPS: 90
i: 13	Total OPS: 97
i: 14	Total OPS: 104
i: 15	Total OPS: 111
i: 16	Total OPS: 118
i: 17	Total OPS: 125
i: 18	Total OPS: 132
i: 19	Total OPS: 139
i: 20	Total OPS: 146
i: 21	Total OPS: 152
*/