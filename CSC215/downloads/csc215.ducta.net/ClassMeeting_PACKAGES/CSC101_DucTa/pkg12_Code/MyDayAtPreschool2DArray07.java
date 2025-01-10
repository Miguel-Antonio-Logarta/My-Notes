package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray07 {

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

        // Display the content of the original array
        System.out.println("The orginal array:");
        display2DArrays(alphabetAndAnimals);
        // Add 1 new column to the left. This column contains index+1
        Object[][] alphabetAndAnimalsWithIndexes = generateNew2DArray(alphabetAndAnimals);
        // Display the content of the new array
        System.out.println("\nThe new array:");
        display2DArrays(alphabetAndAnimalsWithIndexes);
    }

    public static Object[][] generateNew2DArray(Object[][] objectArray) {

        Object[][] newArray = new Object[26][3];

        for (int rowIndex = 0; rowIndex < objectArray.length; rowIndex++) {

            newArray[rowIndex][0] = rowIndex + 1;

            for (int columnIndex = 0; columnIndex < objectArray[rowIndex].length; columnIndex++) {
                newArray[rowIndex][columnIndex + 1] = objectArray[rowIndex][columnIndex];
            }
        }
        return newArray;
    }

    public static void display2DArrays(Object[][] objectArray) {
        for (int rowIndex = 0; rowIndex < objectArray.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < objectArray[rowIndex].length; columnIndex++) {
                System.out.print(objectArray[rowIndex][columnIndex] + "\t");
            }
            System.out.println("");
        }
    }

}

/* OUTPUT
A day at my preschool: 

The orginal array:
A	Ant	
B	Bat	
C	Cat	
D	Dog	
E	Emu	
F	Fox	
G	Gar	
H	Hare	
I	Ibis	
J	Jackal	
K	Kiwi	
L	Lion	
M	Moth	
N	Newt	
O	Owl	
P	Pig	
Q	Quail	
R	Rat	
S	Seal	
T	Tang	
U	Uakari	
V	Viper	
W	Wolf	
X	Xerus	
Y	Yak	
Z	Zebu	

The new array:
1	A	Ant	
2	B	Bat	
3	C	Cat	
4	D	Dog	
5	E	Emu	
6	F	Fox	
7	G	Gar	
8	H	Hare	
9	I	Ibis	
10	J	Jackal	
11	K	Kiwi	
12	L	Lion	
13	M	Moth	
14	N	Newt	
15	O	Owl	
16	P	Pig	
17	Q	Quail	
18	R	Rat	
19	S	Seal	
20	T	Tang	
21	U	Uakari	
22	V	Viper	
23	W	Wolf	
24	X	Xerus	
25	Y	Yak	
26	Z	Zebu
*/