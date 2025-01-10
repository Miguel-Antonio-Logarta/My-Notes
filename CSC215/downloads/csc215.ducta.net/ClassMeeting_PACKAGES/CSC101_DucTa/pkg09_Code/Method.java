package pkg09_Code;

/*
 * @author Duc Ta
 */

class Method01x {

    public static void main(String[] args) {
        displayGreeting();
    }

    public static void displayGreeting() {
        System.out.println("Happy Thanksgiving!");
    }
}
/* OUTPUT
Happy Thanksgiving!
*/


class Method02x {

    public static void main(String[] args) {
        displayGreeting("Thanksgiving");
    }

    public static void displayGreeting(String s) {
        System.out.println("Happy " + s + "!");
    }
}
/* OUTPUT
Happy Thanksgiving!
*/


class Method03x {

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