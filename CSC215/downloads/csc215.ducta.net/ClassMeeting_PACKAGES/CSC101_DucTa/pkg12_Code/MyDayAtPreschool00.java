package pkg12_Code;

/**
 * @author Duc Ta
 */

public class MyDayAtPreschool00 {

    public static void main(String[] args) {

        System.out.println("A day at my preschool: \n");

        char[] alphabet = {'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y',
                'Z'};

        String[] animals = {"Ant", "Bat", "Cat", "Dog", "Emu",
                "Fox", "Gar", "Hare", "Ibis", "Jackal",
                "Kiwi", "Lion", "Moth", "Newt", "Owl",
                "Pig", "Quail", "Rat", "Seal", "Tang",
                "Uakari", "Vulture", "Wolf", "Xerus", "Yak",
                "Zebu"};

        System.out.println("INDEX\tALPHABET\tANIMAL\t\tASMT03\tASMT03");

        for (int i = 0; i < alphabet.length; i++) {
            System.out.println(i + 1 + "\t" + alphabet[i] + "\t\t" + animals[i]);
        }
    }
}

/* 
A day at my preschool: 

INDEX	ALPHABET	ANIMAL		ASMT03	ASMT03
1	A		Ant
2	B		Bat
3	C		Cat
4	D		Dog
5	E		Emu
6	F		Fox
7	G		Gar
8	H		Hare
9	I		Ibis
10	J		Jackal
11	K		Kiwi
12	L		Lion
13	M		Moth
14	N		Newt
15	O		Owl
16	P		Pig
17	Q		Quail
18	R		Rat
19	S		Seal
20	T		Tang
21	U		Uakari
22	V		Vulture
23	W		Wolf
24	X		Xerus
25	Y		Yak
26	Z		Zebu
*/