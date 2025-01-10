import java.util.InputMismatchException;
import java.util.Scanner;

public class MetricConcreteStrategy implements IMeasurementStrategy{

    /**
     * @param name
     * @return
     */
    @Override
    public int getHeight(String name) {
        int centimeters;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.printf("Please enter in centimeters for %s: ", name);
            try {
                centimeters = sc.nextInt();
                return centimeters;
            } catch (InputMismatchException e) {
                System.out.println("Your input was invalid. Please enter a number: [centimeters]");
                sc.nextLine();
            }
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public double getWeight(String name) {
        Scanner sc = new Scanner(System.in);
        double weight;

        while (true) {
            System.out.printf("Please enter weight in kilograms for %s: ", name);
            try {
                weight = sc.nextDouble();
                return weight;
            } catch (InputMismatchException e) {
                System.out.println("Your input was invalid. Please enter a number (decimals allowed).");
                sc.nextLine();
            }
        }
    }

    /**
     * @param name
     * @param BMI
     * @param BMICategory
     */
    @Override
    public void printSummary(String name, double BMI, String BMICategory) {

    }

    /**
     * @param height
     * @param weight
     * @return
     */
    @Override
    public double calculateBMI(int height, int weight) {
        return 0;
    }

    /**
     *
     */
    @Override
    public void printProgramHeader() {

    }
}
