import java.text.SimpleDateFormat;
import java.util.*;

public interface BMIProgram {
    public int getHeight(String name);
    public double getWeight(String name);
    public double calculateBMI(int height, int weight);
    public void printProgramHeader();

    public static String getName() {
        // I'm not going to check if there are two words because people's full names can get complicated.
        // I'll just take the full string and assume from there.
        String name;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Please enter your full name: ");
            name = sc.nextLine();
        } while (name.isBlank());

        return name;
    }

    public static double getWeight(String name, String message, String errorMessage) {
        Scanner sc = new Scanner(System.in);
        double weight;

        while (true) {
            System.out.print(message);
            try {
                weight = sc.nextDouble();
                return weight;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                sc.nextLine();
            }
        }
    }

    public static void printSummary(String name, double BMI, String BMICategory) {
        // Date string outputs as example: September 26, 2024 at 07:03:41 PM
        String dateString = new SimpleDateFormat("MMMM dd, y 'at' hh:mm:ss a").format(new Date());

        System.out.println();
        System.out.printf("-- SUMMARY REPORT FOR %s\n", name.toUpperCase());
        System.out.printf(Utilities.padRight("-- Date and Time:", 20) + "%s\n", dateString);

        // 6 digits of precision, rounded is 1 digit of precision
        // printf rounds the number for you which is pretty convenient
        System.out.printf(Utilities.padRight("-- BMI:", 20) + "%.6f (or %.1f if rounded)\n", BMI, BMI);

        System.out.printf(Utilities.padRight("-- Weight Status:", 20) + "%s\n", BMICategory);
        System.out.println();
    }

    public static void printProgramFooter(String name) {
        ArrayList<String> customGoodByeMessages = new ArrayList<>();
        customGoodByeMessages.add("Goodbye!");
        customGoodByeMessages.add("Au revoir!");
        customGoodByeMessages.add("Sayonara!");
        customGoodByeMessages.add("Adiós!");
        customGoodByeMessages.add("Auf Wiedersehen!");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd.");
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.printf("-- Thank you for using my program, %s!\n", name);
        System.out.printf("-- %s\n", customGoodByeMessages.get(new Random().nextInt(customGoodByeMessages.size())));
        System.out.println("-".repeat(100));
    }

    public static String getBMICategory(double BMI) {
        if (BMI < 18.5) {
            return "Underweight";
        } else if (BMI >= 18.5 && BMI < 25.0) {
            return "Healthy Weight";
        } else if (BMI >= 25.0 && BMI < 30.0) {
            return "Overweight";
        } else { // BMI over 30 is obesity
            return "Obesity";
        }
    }
}
