package pkg05_Code;

public class ANSI_Colors {
    public static void main(String[] args) {
        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("Before");
        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_WHITE + "Test colors..." + ANSI_RESET);
        System.out.println("After");
    }
}
// ANSI Escape Code: https://en.wikipedia.org/wiki/ANSI_escape_code

// Please run the code to see the colors

/* OUTPUT
Before
Test colors...
After
*/