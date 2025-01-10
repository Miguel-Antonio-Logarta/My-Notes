package pkg06_Code;

/**
 * @author Duc Ta
 */

public class String_concat {
    public static void main(String[] args) {
        String string1 = "TA";
        String string2 = "DA";

        System.out.println(string1.concat(string2));

        System.out.println(string1);
        System.out.println(string2);

        String string3 = string1 + string2;
        System.out.println(string3);
    }
}
/* OUTPUT
TADA
TA
DA
TADA

 */