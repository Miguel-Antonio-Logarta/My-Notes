package pkg07_Code;

/**
 * @author ducta
 */

public class ContinueControl {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.print("Row " + i + ": ");
            if (i == 1 || i == 3) {
                System.out.println("Continuing...");
                continue;
            }
            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }

        System.out.println("Out of nested loops!");

    }
}

/* OUTPUT
Row 0: 0 1 2 3 4
Row 1: Continuing...
Row 2: 0 1 2 3 4
Row 3: Continuing...
Row 4: 0 1 2 3 4
Out of nested loops!

 */