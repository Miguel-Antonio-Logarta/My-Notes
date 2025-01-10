package pkg07_Code;

/**
 * @author ducta
 */
public class BreakControl {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < 5; j++) {
                System.out.print(j + " ");
                if (j == 3) {
                    System.out.print("Breaking...");
                    break;
                }
            }
            System.out.println("");
        }
        System.out.println("Out of nested loops!");

    }
}
/* OUTPUT
Row 0: 0 1 2 3 Breaking...
Row 1: 0 1 2 3 Breaking...
Row 2: 0 1 2 3 Breaking...
Row 3: 0 1 2 3 Breaking...
Row 4: 0 1 2 3 Breaking...
Out of nested loops!

 */