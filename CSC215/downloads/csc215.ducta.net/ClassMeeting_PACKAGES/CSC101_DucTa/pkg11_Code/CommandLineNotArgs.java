package pkg11_Code;

/**
 * @author Duc Ta
 */

public class CommandLineNotArgs {

    public static void main(String[] s) {

        String dataType;

        for (int i = 0; i < s.length; i++) {
            dataType = s[i].getClass().getSimpleName();
            System.out.println("args[" + i + "] \t" + dataType + " \t" + s[i]);
        }
    }

}
