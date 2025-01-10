import java.text.SimpleDateFormat;
import java.util.*;

class BMI_CSC215_Metric_MiguelLogarta {
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

        /**
         * Prompts the user for their height in feet and inches.
         * @return the height of the user in inches
         */
        public static int getHeightEnglish(String name) {
            int feet;
            int inches;
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.printf("Please enter height in feet and inches for %s: ", name);
                try {
                    feet = sc.nextInt();
                    inches = sc.nextInt();
                    return feet * 12 + inches;
                } catch (InputMismatchException e) {
                    System.out.println("Your input was invalid. Please enter two numbers separated by a space: [feet] [inches]");
                    sc.nextLine();
                }
            }
        }

    /**
     * Prompts the user for their height in centimeters
     * @return the height of the user in inches
     */
    public static int getHeightMetric(String name) {
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

    /**
     * Returns a BMI using the English measurement system
     * @param height height in inches
     * @param weight weight in pounds
     * @return BMI
     */
    public static double calculateBMIEnglish(int height, double weight) {
        return (weight / Math.pow(height, 2.0)) * 703.0;
    }

    /**
     * Returns a BMI using the Metric measurement system
     * @param height height in centimeters
     * @param weight weight in kilograms
     * @return BMI
     */
    public static double calculateBMIMetric(int height, double weight) {
        return (weight) / (Math.pow((double)height / (double)100, 2));
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

    public static void printProgramHeaderEnglish() {
        System.out.println("-".repeat(100));
        System.out.println("-- Welcome to:");
        System.out.println("--              BODY MASS INDEX (BMI) Computation, CSC 215, English version");
        System.out.println("--                                                                     By: Miguel Logarta");
        System.out.println("-".repeat(100));
    }

    public static void printProgramHeaderMetric() {
        System.out.println("-".repeat(100));
        System.out.println("-- Welcome to:");
        System.out.println("--              BODY MASS INDEX (BMI) Computation, CSC 215, Metric version");
        System.out.println("--                                                                     By: Miguel Logarta");
        System.out.println("-".repeat(100));
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

    public static void main(String[] args) {

        String name;
        int height;
        double weight;
        double lowWeight;
        double highWeight;
        double BMI;
        String BMICategory;
        BMITable bmiTable = new BMITable();

        printProgramHeaderMetric();

        name = getName();
        height = getHeightMetric(name);
        weight = getWeight(name,
                String.format("Please enter weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        BMI = calculateBMIMetric(height, weight);
        BMICategory = getBMICategory(BMI);

        printSummary(name, BMI, BMICategory);

        lowWeight = getWeight(name,
                String.format("Please enter a LOW weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        highWeight = getWeight(name,
                String.format("Please enter a HIGH weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );

        bmiTable.setTargetBMI(height, weight);
        bmiTable.generateTable(lowWeight, highWeight, 25);
        bmiTable.renderTable();

        printProgramFooter(name);
    }
}