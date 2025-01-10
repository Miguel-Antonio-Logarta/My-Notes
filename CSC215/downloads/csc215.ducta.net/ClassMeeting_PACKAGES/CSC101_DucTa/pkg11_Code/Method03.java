package pkg11_Code;

/**
 * @author Duc Ta
 */

public class Method03 {

    public static void main(String[] args) {
        String greeting = displayGreeting("Thanksgiving");
        System.out.println(greeting);
    }

    public static String displayGreeting(String s) {
        String resultS = "Happy " + s + "!";
        return resultS;
    }
}

/* OUTPUT
Happy Thanksgiving!
*/