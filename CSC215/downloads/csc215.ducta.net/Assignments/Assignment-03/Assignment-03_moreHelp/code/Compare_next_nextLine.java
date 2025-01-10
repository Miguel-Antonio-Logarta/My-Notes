package asmt03_moreHelp;

import java.util.Scanner;

public class Compare_next_nextLine {
    public static void main(String[] args) {

        String line1 = "Line#1 line#1Word2 line#1Word3\n"; // Notice the space
        String line2 = "Line#2 line#2Word2 line#2Word3\n";
        String line3 = "Line#3 line#3Word2 line#3Word3\n";
        String line4 = "Line#4 line#4Word2 line#4Word3\n";
        String line5 = "Line#5 line#5Word2 line#5Word3\n";

        String paragraphInput = line1 + line2 + line3 + line4 + line5;
        Scanner input = new Scanner(paragraphInput);

        System.out.println("Call #1, input.nextLine(): " + input.nextLine());
        System.out.println("Call #2, input.nextLine(): " + input.nextLine());
        System.out.println("Call #3, input.next(): " + input.next());
        System.out.println("Call #4, input.next(): " + input.next());
        System.out.println("Call #4, input.next(): " + input.next());
    }
}

/* OUTPUT
Call #1, input.nextLine(): Line#1 line#1Word2 line#1Word3
Call #2, input.nextLine(): Line#2 line#2Word2 line#2Word3
Call #3, input.next(): Line#3
Call #4, input.next(): line#3Word2
Call #4, input.next(): line#3Word3

 */