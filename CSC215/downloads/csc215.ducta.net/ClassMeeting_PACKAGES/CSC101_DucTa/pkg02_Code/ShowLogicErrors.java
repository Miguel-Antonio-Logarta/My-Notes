package pkg02_Code;

public class ShowLogicErrors {

    public static void main(String[] args) {
        System.out.println("Celsius 35 is Fahrenheit degree ");
        System.out.println((9 / 5) * 35 + 32);  // 67, wrong, 9/5 = 1
        System.out.println((9.0 / 5) * 35 + 32);  // 67, wrong 

    }
}
