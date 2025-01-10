package pkg11_Code;

/**
 * @author Duc Ta
 */

public class CommandLineVarargsDemo {

    public static void main(String... args) {

        String dataType;

        for (int i = 0; i < args.length; i++) {
            dataType = args[i].getClass().getSimpleName();
            System.out.println("args[" + i + "] \t" + dataType + " \t" + args[i]);
        }
    }

}
