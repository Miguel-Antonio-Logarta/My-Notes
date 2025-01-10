package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray05 {

    public static void main(String[] args) {

        System.out.println("A day at my preschool: \n");

        Object[][] alphabetAndAnimals = {
            {'A', "Ant"},
            {'B', "Bat"},
            {'C', "Cat"},
            {'D', "Dog"},
            {'E', "Emu"},
            {'F', "Fox"},
            {'G', "Gar"},
            {'H', "Hare"},
            {'I', "Ibis"},
            {'J', "Jackal"},
            {'K', "Kiwi"},
            {'L', "Lion"},
            {'M', "Moth"},
            {'N', "Newt"},
            {'O', "Owl"},
            {'P', "Pig"},
            {'Q', "Quail"},
            {'R', "Rat"},
            {'S', "Seal"},
            {'T', "Tang"},
            {'U', "Uakari"},
            {'V', "Viper"},
            {'W', "Wolf"},
            {'X', "Xerus"},
            {'Y', "Yak"},
            {'Z', "Zebu"}
        };

        display2DArrays(alphabetAndAnimals);
    }

    public static void display2DArrays(Object[][] objectArray) {
        for (int rowIndex = 0; rowIndex < objectArray.length; rowIndex++) {

            System.out.print(rowIndex + "\t");

            for (int columnIndex = 0; columnIndex < objectArray[rowIndex].length; columnIndex++) {
                System.out.print(objectArray[rowIndex][columnIndex] + "\t");
            }
            System.out.println("");
        }
    }

}
/*
A day at my preschool: 

0	A	Ant	
1	B	Bat	
2	C	Cat	
3	D	Dog	
4	E	Emu	
5	F	Fox	
6	G	Gar	
7	H	Hare	
8	I	Ibis	
9	J	Jackal	
10	K	Kiwi	
11	L	Lion	
12	M	Moth	
13	N	Newt	
14	O	Owl	
15	P	Pig	
16	Q	Quail	
17	R	Rat	
18	S	Seal	
19	T	Tang	
20	U	Uakari	
21	V	Viper	
22	W	Wolf	
23	X	Xerus	
24	Y	Yak	
25	Z	Zebu	
*/