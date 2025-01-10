public class Utilities {
    /**
     * Deletes any ANSI codes present in the string. Useful utility tool if you want to
     * check the length of a string without the ANSI codes inside of them. Uses regex to
     * accomplish the task.
     * @param s string containing ANSI codes. If it doesn't have any, it'll just return s.
     * @return string with the ANSI codes removed.
     */
    public static String stripAnsiCodes(String s) {
        return s.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "");
    }

    /**
     * Pads a string s on the right with n spaces " "
     * @param s string to be modified
     * @param n number of spaces to insert
     * @return a space-padded string
     */
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    /**
     * Pads string s on the left with n spaces " "
     * @param s string to be modified
     * @param n number of spaces to insert
     * @return a space-padded string
     */
    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
