package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray03 {

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
                {"Uakari", "Viper", "Wolf", "Xerus", "Yak"},
                {"Zebu"}
        };

        display2DArrays(alphabet, animals);
    }

    public static void display2DArrays(char[][] charArray, String[][] stringArray) {
        for (int rowIndex = 0; rowIndex < charArray.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < charArray[rowIndex].length; columnIndex++) {
                System.out.print("[" + rowIndex + "][" + columnIndex + "]"
                        + charArray[rowIndex][columnIndex] + "->"
                        + stringArray[rowIndex][columnIndex]
                        + "\t");
            }
            System.out.println("");
        }
    }

}
/*
A day at my preschool: 

[0][0]A->Ant	[0][1]B->Bat	[0][2]C->Cat	[0][3]D->Dog	[0][4]E->Emu	
[1][0]F->Fox	[1][1]G->Gar	[1][2]H->Hare	[1][3]I->Ibis	[1][4]J->Jackal	
[2][0]K->Kiwi	[2][1]L->Lion	[2][2]M->Moth	[2][3]N->Newt	[2][4]O->Owl	
[3][0]P->Pig	[3][1]Q->Quail	[3][2]R->Rat	[3][3]S->Seal	[3][4]T->Tang	
[4][0]U->Uakari	[4][1]V->Viper	[4][2]W->Wolf	[4][3]X->Xerus	[4][4]Y->Yak	
[5][0]Z->Zebu	
*/