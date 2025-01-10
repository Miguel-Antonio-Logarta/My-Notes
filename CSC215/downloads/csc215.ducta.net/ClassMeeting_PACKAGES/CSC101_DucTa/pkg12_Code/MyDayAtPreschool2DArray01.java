package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray01 {

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
        display2DArray(animals);
    }

    public static void display2DArray(char[][] a) {
        for (char[] row : a) {
            for (char col : row) {
                System.out.print(col + "\t");
            }
            System.out.println("");
        }
    }

    public static void display2DArray(String[][] a) {

        for (String[] row : a) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println("");
        }

    }
}
/*
A day at my preschool: 

A	B	C	D	E	
F	G	H	I	J	
K	L	M	N	O	
P	Q	R	S	T	
U	V	W	X	Y	
Z	
Ant	Bat	Cat	Dog	Emu	
Fox	Gar	Hare	Ibis	Jackal	
Kiwi	Lion	Moth	Newt	Owl	
Pig	Quail	Rat	Seal	Tang	
Uakari	Vulture	Wolf	Xerus	Yak	
Zebu	
*/