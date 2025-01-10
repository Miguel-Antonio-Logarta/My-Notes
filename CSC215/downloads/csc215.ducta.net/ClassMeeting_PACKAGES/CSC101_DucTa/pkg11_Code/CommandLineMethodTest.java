package pkg11_Code;

/**
 * @author Duc Ta PLUS
 */
public class CommandLineMethodTest {

    public static void main(String[] args) {
        String arguments[] = {"Mickey", "Minnie", "Goofy"};
        CommandLineDemo.main(arguments);

        display(arguments);
    }

    public static void display(String s[]) {
        for (String item : s) {
            System.out.println(item);
        }
    }
}

/* OUTPUT
args[0]	String	Mickey
args[1]	String	Minnie
args[2]	String	Goofy
Mickey
Minnie
Goofy

 */