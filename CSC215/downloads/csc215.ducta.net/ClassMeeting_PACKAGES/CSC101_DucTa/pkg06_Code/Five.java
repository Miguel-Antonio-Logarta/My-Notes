package pkg06_Code;

/**
 * @author Duc Ta
 */

public class Five {
    public static void main(String[] args) {
        System.out.println("5");
        char c = '5';
        System.out.println(Character.isDigit(c));
        System.out.println(Character.isLetter(c));
        System.out.println(Character.isLetterOrDigit(c));
        System.out.println(Character.isLowerCase(c));
        System.out.println(Character.isUpperCase(c));

        System.out.println();
        System.out.println("f");
        c = 'f';
        System.out.println(Character.isDigit(c));
        System.out.println(Character.isLetter(c));
        System.out.println(Character.isLetterOrDigit(c));
        System.out.println(Character.isLowerCase(c));
        System.out.println(Character.isUpperCase(c));
    }
}

/* OUTPUT
true
false
true
false
false

 */