package pkg12_Code;

/**
 * @author Duc Ta PLUS
 */

public class MyDayAtPreschool2DArray08 {

    public static void main(String[] args) {
        Object[] objectArray = new Object[4];

        int integerVar = 1;
        objectArray[0] = integerVar;

        double doubleVar = 2.1;
        objectArray[1] = doubleVar;

        char charVar = 'a';
        objectArray[2] = charVar;

        String stringVar = "a string";
        objectArray[3] = stringVar;

        for (Object item : objectArray) {
            // What data type?
            System.out.print(item.getClass().getSimpleName());
            // Display item
            System.out.print(" ---> " + item + "\n");
        }
    }
}

/* OUTPUT 
Integer ---> 1
Double ---> 2.1
Character ---> a
String ---> a string
*/