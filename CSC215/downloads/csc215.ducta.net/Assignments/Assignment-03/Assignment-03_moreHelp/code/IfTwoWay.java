package asmt03_moreHelp;

/**
 * @author Duc Ta
 */

public class IfTwoWay {
    public static void main(String[] args) {

        double area;
        double radius = 3;  // Case: User inputs a value for radius.
                            //       Test positive input

        if (radius >= 0) {
            area = radius * radius * 3.14159;
            System.out.println("The area for the circle is " + area);
        } else {
            System.out.println("Negative input");
        }

    }
}

/* OUTPUT
The area for the circle is 28.27431
 */