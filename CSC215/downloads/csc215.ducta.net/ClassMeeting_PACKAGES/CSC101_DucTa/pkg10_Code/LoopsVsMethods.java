package pkg10_Code;

/**
 * @author Duc Ta
 */

public class LoopsVsMethods {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }

        System.out.println("\n Doing other things...\n");

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

 Doing other things...

Row 0: 0 1 2 3 4
Row 1: 0 1 2 3 4
Row 2: 0 1 2 3 4
Row 3: 0 1 2 3 4
Row 4: 0 1 2 3 4

 */