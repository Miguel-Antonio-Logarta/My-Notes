package asmt03_moreHelp;

/**
 * @author Duc Ta
 */

public class Loop_while_and_break {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            if (i < 10) {
                System.out.println(i + ". \"Life is what happens to you while you're busy making other plans\" - John Lennon.");
            } else {
                break;
            }
            i++;
        }
    }
}

/* OUTPUT
1. "Life is what happens to you while you're busy making other plans" - John Lennon.
2. "Life is what happens to you while you're busy making other plans" - John Lennon.
3. "Life is what happens to you while you're busy making other plans" - John Lennon.
4. "Life is what happens to you while you're busy making other plans" - John Lennon.
5. "Life is what happens to you while you're busy making other plans" - John Lennon.
6. "Life is what happens to you while you're busy making other plans" - John Lennon.
7. "Life is what happens to you while you're busy making other plans" - John Lennon.
8. "Life is what happens to you while you're busy making other plans" - John Lennon.
9. "Life is what happens to you while you're busy making other plans" - John Lennon.

 */