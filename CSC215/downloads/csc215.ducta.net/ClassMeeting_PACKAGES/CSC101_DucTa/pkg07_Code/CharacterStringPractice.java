package pkg07_Code;

import java.util.Scanner;

/**
 * @author Duc Ta PLUS
 */

public class CharacterStringPractice {

    public static void main(String[] args) {

        String sOriginal;
        Scanner input = new Scanner(System.in);
        System.out.print("Your short sentence: ");
        sOriginal = input.nextLine();

        String sUpper = sOriginal.toUpperCase(); // not reused
        String sLower = sOriginal.toLowerCase(); // to be reused

        System.out.println("\nOriginal String: " + sOriginal);
        System.out.println("To upper case  : " + sUpper);
        System.out.println("To lower case  : " + sLower);

        //--------------------------------------------------------------------
        int totalE = 0;
        int i = 0;
        while (i < sLower.length()) {
            if (sLower.charAt(i) == 'e') {
                totalE++;
            }
            i++;
        }
        // for (int i = 1; i < sLower.length(); i++)

        if (totalE == 0) {
            System.out.println("No e/E found!");
        } else {
            System.out.println(totalE + " e/E found!");
        }
        // Also see: equalsIgnoreCase, compareToIgnoreCase, logical operator && || 

        char lastChar = sOriginal.charAt(sOriginal.length() - 1);
        System.out.println("The last character is: " + lastChar);

        //--------------------------------------------------------------------
        // First character of all words to upper. Handling very simple strings.
        char cPrevious = 0, cCurrent = 0;
        String sUpperFirstChars = sLower.substring(0, 1).toUpperCase();

        for (int j = 1; j < sLower.length(); j++) {

            cCurrent = sLower.charAt(j);

            if (cPrevious == ' ') { // Unicode Space 32
                cCurrent = Character.toUpperCase(cCurrent);
            }

            sUpperFirstChars = sUpperFirstChars.concat(String.valueOf(cCurrent));
            //sUpperFirstChars += String.valueOf(cCurrent);
            cPrevious = cCurrent;
        }

        System.out.println("All first to upper: " + sUpperFirstChars);
    }
}

/* OUTPUT 
Your short sentence: the FEw tHE pRouD

Original String: the FEw tHE pRouD
To upper case  : THE FEW THE PROUD
To lower case  : the few the proud
3 e/E found!
The last character is: D
All first to upper: The Few The Proud
*/
