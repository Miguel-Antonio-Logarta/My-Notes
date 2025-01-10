package pkg04_Code;

/**
 * @author Duc Ta
 */

public class Operator_Ternary {
    public static void main(String[] args) {
        boolean wantToDoWell = true;
        String instruction = (wantToDoWell) ? "active and meaningful participation" : "sleep in";
        System.out.println(instruction);
    }
}

/* OUTPUT
active and meaningful participation
 */
