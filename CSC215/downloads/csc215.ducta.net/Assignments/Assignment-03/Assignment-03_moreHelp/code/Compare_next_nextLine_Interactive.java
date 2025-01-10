package asmt03_moreHelp;

import java.util.Scanner;

public class Compare_next_nextLine_Interactive {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter a line: ");
        String nextLine = input.nextLine();
        System.out.println("nextLine() grabbed: " + nextLine);

        System.out.print("Please enter a line: ");
        nextLine = input.nextLine();
        System.out.println("nextLine() grabbed: " + nextLine);

        System.out.println("\n\nNow, please watch carefully\n");
        System.out.print("Please enter a line: ");
        String next = input.next();

        System.out.println("next() grabbed: " + next);
        next = input.next();
        System.out.println("next() grabbed: " + next);
        next = input.next();
        System.out.println("next() grabbed: " + next);
        next = input.next();

    }
}

/* OUTPUT
Please enter a line: He is a GOAT!
nextLine() grabbed: He is a GOAT!
Please enter a line: She is a GOAT!
nextLine() grabbed: She is a GOAT!


Now, please watch carefully

Please enter a line: GOAT means Greatest of All Time.
next() grabbed: GOAT
next() grabbed: means
next() grabbed: Greatest

 */