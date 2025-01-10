package pkg05_Code;

public class Loop_while_continue {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 7) {
            System.out.println(i + " sheep");
            i++;
            if (i <= 5) {
                continue;
            }
            System.out.println(i + " sleeeeepyyyy");
        }

        System.out.println("zzzzzZZZZZZ");
    }
}

/* OUTPUT
1 sheep
2 sheep
3 sheep
4 sheep
5 sheep
6 sleeeeepyyyy
6 sheep
7 sleeeeepyyyy
7 sheep
8 sleeeeepyyyy
zzzzzZZZZZZ

 */