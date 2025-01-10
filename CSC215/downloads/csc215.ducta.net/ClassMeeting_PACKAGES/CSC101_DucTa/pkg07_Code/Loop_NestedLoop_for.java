package pkg07_Code;

/**
 * @author ducta
 */

public class Loop_NestedLoop_for {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.print("Row " + i + ": ");

            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");
            }

            System.out.println("");
        }

    }
}

/* OUTPUT
Row 0: 0 1 2 3 4
Row 1: 0 1 2 3 4
Row 2: 0 1 2 3 4
Row 3: 0 1 2 3 4
Row 4: 0 1 2 3 4

 */