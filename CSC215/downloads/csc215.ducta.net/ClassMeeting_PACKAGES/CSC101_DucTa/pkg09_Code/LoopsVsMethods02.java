package pkg09_Code;

/**
 * @author Duc Ta
 */

public class LoopsVsMethods02 {

    public static void main(String[] args) {
        traverseBuilding(5, 5);
        traverseBuilding(2, 5);
        traverseBuilding(1, 8);
        traverseBuilding(5, 3);
    }

    public static void traverseBuilding(int floors, int offices) {
        System.out.println("Method STARTS");
        for (int i = 0; i < floors; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < offices; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
        System.out.println("Method ENDS\n");
    }
}

/* OUTPUT
Method STARTS
Row 0: 0 1 2 3 4
Row 1: 0 1 2 3 4
Row 2: 0 1 2 3 4
Row 3: 0 1 2 3 4
Row 4: 0 1 2 3 4
Method ENDS

Method STARTS
Row 0: 0 1 2 3 4
Row 1: 0 1 2 3 4
Method ENDS

Method STARTS
Row 0: 0 1 2 3 4 5 6 7
Method ENDS

Method STARTS
Row 0: 0 1 2
Row 1: 0 1 2
Row 2: 0 1 2
Row 3: 0 1 2
Row 4: 0 1 2
Method ENDS

 */