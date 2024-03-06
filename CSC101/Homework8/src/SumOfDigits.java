import java.util.Random;

public class SumOfDigits {
    public static void main(String[] args) {
        Random rand = new Random();
        int threeDigit = rand.nextInt(100, 1000);
        System.out.print(threeDigit + " has the sum of digits: ");

        int sum = 0;
        int divisor = 1;
        while (threeDigit/divisor != 0) {
            sum += (threeDigit/divisor)%10;
            divisor = divisor*10;
        }

        System.out.print(sum);
    }
}
