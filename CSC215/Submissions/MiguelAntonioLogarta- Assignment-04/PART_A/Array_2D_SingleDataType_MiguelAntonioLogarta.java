package PART_A;

public class Array_2D_SingleDataType_MiguelAntonioLogarta {
    public static void printArray(char[][] arr, int padding) {
        // Get the longest row in m x n matrix
        int maxLength = 0;
        for (char[] row : arr) {
            if (maxLength < row.length) {
                maxLength = row.length;
            }
        }

        for (char[] row : arr) {
            int n = row.length;
            int spaces = (padding + 1) * (maxLength - n);
            System.out.print(" ".repeat(spaces));   // Right justify
            for (char element : row) {
                System.out.print(element);
                System.out.print(" ".repeat(padding));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int m = 7;
        int n = 7;

        // Standard alphabet array declared with shorthand approach
        char[][] standardAlphabet = {
                {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
                {' ', ' ', ' ', 'H', 'I', 'J', 'K'},
                {' ', ' ', 'L', 'M', 'N', 'O', 'P'},
                {' ', ' ', ' ', ' ', 'Q', 'R', 'S'},
                {' ', ' ', ' ', ' ', 'T', 'U', 'V'},
                {' ', ' ', ' ', ' ', ' ', 'W', 'X'},
                {' ', ' ', ' ', ' ', ' ', 'Y', 'Z'},
        };

        // Staggered alphabet array declared with multistep approach
        char [][] staggeredAlphabet = new char[m][];
        staggeredAlphabet[0] = new char[n];
        staggeredAlphabet[1] = new char[n - 3];
        staggeredAlphabet[2] = new char[n - 2];
        staggeredAlphabet[3] = new char[n - 4];
        staggeredAlphabet[4] = new char[n - 4];
        staggeredAlphabet[5] = new char[n - 5];
        staggeredAlphabet[6] = new char[n - 5];

        // First row
        staggeredAlphabet[0][0] = 'A';
        staggeredAlphabet[0][1] = 'B';
        staggeredAlphabet[0][2] = 'C';
        staggeredAlphabet[0][3] = 'D';
        staggeredAlphabet[0][4] = 'E';
        staggeredAlphabet[0][5] = 'F';
        staggeredAlphabet[0][6] = 'G';

        // Second row
        staggeredAlphabet[1][0] = 'H';
        staggeredAlphabet[1][1] = 'I';
        staggeredAlphabet[1][2] = 'J';
        staggeredAlphabet[1][3] = 'K';

        // Third row
        staggeredAlphabet[2][0] = 'L';
        staggeredAlphabet[2][1] = 'M';
        staggeredAlphabet[2][2] = 'N';
        staggeredAlphabet[2][3] = 'O';
        staggeredAlphabet[2][4] = 'P';

        // Fourth row
        staggeredAlphabet[3][0] = 'Q';
        staggeredAlphabet[3][1] = 'R';
        staggeredAlphabet[3][2] = 'S';

        // Fifth row
        staggeredAlphabet[4][0] = 'T';
        staggeredAlphabet[4][1] = 'U';
        staggeredAlphabet[4][2] = 'V';

        // Sixth row
        staggeredAlphabet[5][0] = 'W';
        staggeredAlphabet[5][1] = 'X';

        // Seventh row
        staggeredAlphabet[6][0] = 'Y';
        staggeredAlphabet[6][1] = 'Z';

        System.out.println("Displaying contents in any 2D arrays:");
        printArray(standardAlphabet, 4);

        System.out.println();
        System.out.println("Displaying contents in any 2D arrays:");
        printArray(staggeredAlphabet, 4);
    }
}
