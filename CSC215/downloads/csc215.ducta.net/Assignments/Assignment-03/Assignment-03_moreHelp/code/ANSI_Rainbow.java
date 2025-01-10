package asmt03_moreHelp;

/**
 * @author Duc Ta
 */

public class ANSI_Rainbow {

    public static void main(String[] args) {

        final String BLACK = "\u001B[30m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String MAGENTA = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";

        final String BACKGROUND_BLACK = "\u001B[40m";
        final String BACKGROUND_RED = "\u001B[41m";
        final String BACKGROUND_GREEN = "\u001B[42m";
        final String BACKGROUND_YELLOW = "\u001B[43m";
        final String BACKGROUND_BLUE = "\u001B[44m";
        final String BACKGROUND_MAGENTA = "\u001B[45m";
        final String BACKGROUND_CYAN = "\u001B[46m";
        final String BACKGROUND_WHITE = "\u001B[47m";

        final String ANSI_RESET = "\u001B[0m";

        System.out.println("CSC 101");
        System.out.println(BACKGROUND_CYAN + BLACK + "CSC 215" + ANSI_RESET);
        System.out.println("CSC 220");
    }
}

/* OUTPUT
CSC 101
CSC 215
CSC 220

 */