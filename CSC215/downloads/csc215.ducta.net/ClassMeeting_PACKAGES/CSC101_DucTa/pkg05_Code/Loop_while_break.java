package pkg05_Code;

public class Loop_while_break {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 7) {
            System.out.println(i + " sheep");
            if (i == 3) {
                break;
            }
            i++;
        }
    }
}

/* OUTPUT
1 sheep
2 sheep
3 sheep

 */