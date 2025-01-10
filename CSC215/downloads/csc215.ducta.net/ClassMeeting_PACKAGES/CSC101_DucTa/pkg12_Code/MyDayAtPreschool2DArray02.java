package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray02 {

    public static void main(String[] args) {

        System.out.println("A day at my preschool: \n");

        char[][] alphabet = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'},
                {'K', 'L', 'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S', 'T'},
                {'U', 'V', 'W', 'X', 'Y'},
                {'Z'}
        };

        String[][] animals = {
                {"Ant", "Bat", "Cat", "Dog", "Emu"},
                {"Fox", "Gar", "Hare", "Ibis", "Jackal"},
                {"Kiwi", "Lion", "Moth", "Newt", "Owl"},
                {"Pig", "Quail", "Rat", "Seal", "Tang"},
                {"Uakari", "Vulture", "Wolf", "Xerus", "Yak"},
                {"Zebu"}
        };

        display2DArray(alphabet);
        System.out.println("");
        display2DArray(animals);
    }

    public static void display2DArray(char[][] charArray) {
        for (int rowIndex = 0; rowIndex < charArray.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < charArray[rowIndex].length; columnIndex++) {
                System.out.print("[" + rowIndex + "][" + columnIndex + "]"
                        + charArray[rowIndex][columnIndex] + "\t");
            }
            System.out.println("");
        }
    }

    public static void display2DArray(String[][] stringArray) {
        for (int rowIndex = 0; rowIndex < stringArray.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < stringArray[rowIndex].length; columnIndex++) {
                System.out.print("[" + rowIndex + "][" + columnIndex + "]"
                        + stringArray[rowIndex][columnIndex] + "\t");
            }
            System.out.println("");
        }
    }
}
/* OUTPUT 
A day at my preschool: 

[0][0]A	[0][1]B	[0][2]C	[0][3]D	[0][4]E	
[1][0]F	[1][1]G	[1][2]H	[1][3]I	[1][4]J	
[2][0]K	[2][1]L	[2][2]M	[2][3]N	[2][4]O	
[3][0]P	[3][1]Q	[3][2]R	[3][3]S	[3][4]T	
[4][0]U	[4][1]V	[4][2]W	[4][3]X	[4][4]Y	
[5][0]Z	

[0][0]Ant	[0][1]Bat	[0][2]Cat	[0][3]Dog	[0][4]Emu	
[1][0]Fox	[1][1]Gar	[1][2]Hare	[1][3]Ibis	[1][4]Jackal	
[2][0]Kiwi	[2][1]Lion	[2][2]Moth	[2][3]Newt	[2][4]Owl	
[3][0]Pig	[3][1]Quail	[3][2]Rat	[3][3]Seal	[3][4]Tang	
[4][0]Uakari	[4][1]Vulture	[4][2]Wolf	[4][3]Xerus	[4][4]Yak	
[5][0]Zebu
*/