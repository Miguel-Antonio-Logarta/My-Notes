package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool2DArray04 {

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
            for (int columnIndex = 0; columnIndex < objectArray[rowIndex].length; columnIndex++) {
                System.out.print("[" + rowIndex + "][" + columnIndex + "]"
                        + objectArray[rowIndex][columnIndex]);

                if ((columnIndex % 2) == 0) {
                    System.out.print(" -> ");
                }
            }
            System.out.println("");
        }
    }

}
/*
A day at my preschool: 

[0][0]A -> [0][1]Ant
[1][0]B -> [1][1]Bat
[2][0]C -> [2][1]Cat
[3][0]D -> [3][1]Dog
[4][0]E -> [4][1]Emu
[5][0]F -> [5][1]Fox
[6][0]G -> [6][1]Gar
[7][0]H -> [7][1]Hare
[8][0]I -> [8][1]Ibis
[9][0]J -> [9][1]Jackal
[10][0]K -> [10][1]Kiwi
[11][0]L -> [11][1]Lion
[12][0]M -> [12][1]Moth
[13][0]N -> [13][1]Newt
[14][0]O -> [14][1]Owl
[15][0]P -> [15][1]Pig
[16][0]Q -> [16][1]Quail
[17][0]R -> [17][1]Rat
[18][0]S -> [18][1]Seal
[19][0]T -> [19][1]Tang
[20][0]U -> [20][1]Uakari
[21][0]V -> [21][1]Viper
[22][0]W -> [22][1]Wolf
[23][0]X -> [23][1]Xerus
[24][0]Y -> [24][1]Yak
[25][0]Z -> [25][1]Zebu
*/