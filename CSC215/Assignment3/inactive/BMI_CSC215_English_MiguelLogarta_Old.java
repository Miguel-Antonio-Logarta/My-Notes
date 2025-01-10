//Write an interactive Java program that performs these actions:
//a. Prompt the user to enter the user’s name.
//b. Prompt the user to enter height in feet and inches (both integers).
//c. Prompt the user to enter weight in pounds.
//d. Calculate the BMI of the user and display a summary report.
//e. Prompt our user to enter a low weight and a high weight in pounds.
//f. Generate and display a table of BMI information for the entered weight range.
//        - Your program must use at least 5 methods. Use at least one method to perform each of the following tasks:
//a. Display the program’s welcome message.
//b. Get inputs.
//c. Do the calculation(s).
//d. Display the result(s).
//e. Display the program’s ending message.

import java.text.SimpleDateFormat;
import java.util.*;

public class BMI_CSC215_English_MiguelLogarta_Old {

    public static void non_use() {





        /*
         * Various methods to implement:
         *
         * Getting user input  / calculations:
         * getName()
         * getHeight() <- this will adjust depending on the measurement system
         * getWeight() <- this will adjust depending on the measurement system
         * calculateBMI() <- this will adjust depending on the measurement system
         * getDate()
         * getWeightStatus()
         * getLowWeight() <- adjust
         * getHighWeight() <- adjust
         *
         * Printing:
         * printWelcomeHeader()
         * printExitFooter()
         *
         * Table below might need some calculation. Maybe have an object called TableContext()? Use Dependency
         * Injection for that or something
         *
         * TableContext():
         *   colWidths: int[]
         *   totalWidth: int
         *
         * data can be arranged like the following
         * [[149.20, 17.69, Underweight], [...], ...]
         *
         * printTable(data)
         * printTableHeader()
         * printRow()
         * printTableBottom()
         * calculateMinSpacing()
         *
         * 25 rows will be printed out. It's just bmi split between 25 values ranging from LOW to HIGH
         * The widest a row can be is Underweight (LOW)
         * */
        System.out.println("Please enter height in centimeters for {name}: ");

        System.out.println("Please enter weight in kilograms for {name}: ");



        System.out.println("Please enter a LOW weight in pounds for {name}: ");
        System.out.println("Please enter a LOW weight in kilograms for {name}: ");

        System.out.println("Please enter a HIGH weight in pounds for {name}: ");
        System.out.println("Please enter a HIGH weight in kilograms for {name}: ");


        System.out.println("-------");
        System.out.println("-- Thank you for using my program, {name}");
        System.out.println("--              Ear-esistible!!!"); // This can be any goodbye message
        System.out.println("-------");

//        Make a class for a table
//
    }

//    public static int selectProgram() {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("My CSC 215 BMI Calculator Projects");
//        System.out.println("    1. BMI, English");
//        System.out.println("    2. BMI, Metric");
//
//        System.out.println("[ USER MANUAL ] Enter an exclamation mark ! to end.");
//
//        while (true) {
//            System.out.print("Please enter the version you want to try: ");
//            String userInput = sc.nextLine();
//
//            /* *
//            * Regex does a case-insensitive match where each character in 'English' or 'Metric' can be
//            * repeated one or more times.
//            * /english/ matches any substring containing "english"
//            * /\\benglish\\b/ matches "english". \\b means No words in before or after "english".
//            * /\\b(?i)english\\b/ matches "english" and "English". (?i) means case-insensitive search
//            * /\\b(?i)\be+n+g+l+i+s+h+\\b/ matches "ennnggliiish". (+) Allows characters to repeat 1 or more times.
//            * */
//            if (userInput.matches("(?i)\\be+n+g+l+i+s+h+\\b")) {
////                System.out.println("You picked english");
//                return 1;
//            } else if (userInput.matches("(?i)\\bm+e+t+r+i+c+\\b")) {
////                System.out.println("You picked metric");
//                return 2;
//            } else if (userInput.equals("!")) {
//                return -1;
//            } else {
//                System.out.println("Your input was not a valid value");
//            }
//        }
//    }
    public static String padRight(String s, int n) {
        // Pads a string s on the right with n spaces " "
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        // Pads string s on the left with n spaces " "
        return String.format("%" + n + "s", s);
    }

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

    public static double getWeightEnglish(String name) {
        Scanner sc = new Scanner(System.in);
        double weight;

        while (true) {
            System.out.printf("Please enter weight in pounds for %s: ", name);
            try {
                weight = sc.nextDouble();
                return weight;
            } catch (InputMismatchException e) {
                System.out.println("Your input was invalid. Please enter a number (decimals allowed)");
                sc.nextLine();
            }
        }
    }

    public static double getLowWeight(String name) {
        Scanner sc = new Scanner(System.in);
        double lowWeight;

        System.out.println();
        while (true) {
            System.out.printf("Please enter a LOW weight in pounds for %s: ", name);
            try {
                lowWeight = sc.nextDouble();
                return lowWeight;
            } catch (InputMismatchException e) {
                System.out.println("Your input was invalid. Please enter a number (decimals allowed)");
                sc.nextLine();
            }
        }
    }

    public static double getHighWeight(String name) {
        Scanner sc = new Scanner(System.in);
        double highWeight;

        while (true) {
            System.out.printf("Please enter a HIGH weight in pounds for %s: ", name);
            try {
                highWeight = sc.nextDouble();
                return highWeight;
            } catch (InputMismatchException e) {
                System.out.println("Your input was invalid. Please enter a number (decimals allowed)");
                sc.nextLine();
            }
        }
    }

    public static void printSummary(String name, double BMI, String BMICategory) {
        // Date string outputs as example: September 26, 2024 at 07:03:41 PM
        String dateString = new SimpleDateFormat("MMMM dd, y 'at' hh:mm:ss a").format(new Date());

        System.out.println();
        System.out.printf("-- SUMMARY REPORT FOR %s\n", name.toUpperCase());
        System.out.printf(padRight("-- Date and Time:", 20) + "%s\n", dateString);
        // 6 digits of precision, rounded is 1 digit of precision
        // printf rounds the number for you which is pretty convenient
        System.out.printf(padRight("-- BMI:", 20) + "%.6f (or %.1f if rounded)\n", BMI, BMI);
        System.out.printf(padRight("-- Weight Status:", 20) + "%s\n", BMICategory);
    }

    public static double calculateBMIEnglish(int height, double weight) {
        return (weight / Math.pow(height, 2.0)) * 703.0;
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

    public static void printBMITable(double weight, double BMI, int height, double lowWeight, double highWeight, int numRows, int cellPaddingX) {
        // Preliminary Checks. We can move this later
        if (BMI < 0 || lowWeight < 0 || highWeight < 0) {
            System.out.println("BMI, LOW weight, and HIGH weight cannot be negative!");
            return;
        } else if (lowWeight >= highWeight) {
            System.out.println("LOW weight cannot be higher than HIGH weight!");
            return;
        } else if (numRows < 2) {
            System.out.println("Not enough rows to print out a table");
            return;
        }

        // We're going to use a 2D matrix as a data structure for our table
        boolean useTableHeaders = true;
        double closestToUsersBMI;
        double step = (highWeight - lowWeight) / numRows;
        ArrayList<Integer> columnWidths = new ArrayList<Integer>();
        ArrayList<ArrayList<String>>  tableData = new ArrayList<ArrayList<String>>();

        // Insert headers
        ArrayList<String> tableHeaders = new ArrayList<>();
        tableHeaders.add("WEIGHT");
        tableHeaders.add("BMI");
        tableHeaders.add("WEIGHT STATUS");
        tableData.addFirst(tableHeaders);

//        // Track widths of each column
//        columnWidths.add(0);
//        columnWidths.add(0);
//        columnWidths.add(0);

        // Calculate rows
        double currentWeight = lowWeight;
        for (int i = 0; i <= numRows; i++) {
            ArrayList<String> newTableRow = new ArrayList<>();

            // Weight
            // 2 digits of precision, right aligned numbers
            // 7 characters wide, to allow weight limit to be at most 9999.99 lbs
            String formattedRowWeight = padLeft(String.format("%.2f", currentWeight), 7);
            newTableRow.add(formattedRowWeight);

            // BMI
            // 5 digits of precision, left aligned numbers. Don't show trailing zeroes
            double rowBMI = calculateBMIEnglish(height, currentWeight);
            String formattedRowBMI = padRight(String.format("%.5f", rowBMI), 7);
            newTableRow.add(formattedRowBMI);

            // BMI category
            String BMICategory = getBMICategory(rowBMI);
            newTableRow.add(BMICategory);
            tableData.add(newTableRow);

            // Increment
            currentWeight += step;

            // Set max width of each column
            for (int j = 0; j < newTableRow.size(); j++) {
                int currentColumnWidth = newTableRow.get(j).length();
                if (j > columnWidths.size() - 1) {
                    /*
                    * 0 > -1
                    * 1 > 0
                    * 2 > 1
                    * 3 > 2
                    * */
                    columnWidths.add(currentColumnWidth);
                } else if (currentColumnWidth > columnWidths.get(j)) {
                    columnWidths.set(j, currentColumnWidth);
                }
            }
        }

        // Highlight LOW weight, user's BMI, and HIGH weight
        String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        String ANSI_BLACK = "\u001B[30m";
        String RESET = "\033[0m";

        // Set HIGH
        // Get the last element
        int lastRowIndex = tableData.size() - 1;
        ArrayList<String> lastRow = tableData.getLast();

        // Edit the last string of the row
        int lastStringIndex = lastRow.size() - 1;
        String lastString = lastRow.get(lastStringIndex);
        int spacesToAdd = columnWidths.getLast() - lastString.length();

        String highString = lastString + " ".repeat(spacesToAdd + 1) +  ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "(HIGH)" + RESET;

        // Reinsert into table
        lastRow.set(lastStringIndex, highString);
        tableData.set(lastRowIndex, lastRow);

        // Set LOW
        ArrayList<String> firstRow = tableData.get(1);
        lastStringIndex = firstRow.size() - 1;
        lastString = firstRow.get(lastStringIndex);
        spacesToAdd = columnWidths.getLast() - lastString.length();
        String lowString = lastString + " ".repeat(spacesToAdd + 1) +  ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "(LOW)" + RESET;
        firstRow.set(lastStringIndex, lowString);
        tableData.set(1, firstRow);

        // Set (this)
        // Find the index of the row that has the closest weight to the user
        int low = 1;
        int high = tableData.size() - 1;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (Double.parseDouble(tableData.get(mid).getFirst()) == weight) {
                break;
            }

            if (Double.parseDouble(tableData.get(mid).getFirst()) < weight) {
                // If x greater, ignore left half
                low = mid + 1;
            } else {
                // If x is smaller, ignore right half
                high = mid - 1;
            }
        }

        ArrayList<String> thisRow = tableData.get(mid);
        lastStringIndex = thisRow.size() - 1;

        lastString = thisRow.get(lastStringIndex);

        String thisString = lastString + " (this)";
        thisRow.set(lastStringIndex, thisString);
        tableData.set(mid, thisRow);

        // Render table
        // Recalculate last column width
        for (int i = 0; i < tableData.size(); i++) {
//            int columnIndex = tableData.get(i).size() - 1;
            // Get the length without ANSI codes
            int currentColumnWidth = tableData.get(i).getLast().replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
            if (currentColumnWidth > columnWidths.getLast()) {
                columnWidths.set(columnWidths.size() - 1, currentColumnWidth);
            }
        }

        // Print Header
        printRowSeparator(cellPaddingX, columnWidths);

        System.out.print("\n|");
        // Print each cell, pad the data with spaces to align them
        for (int i = 0; i < tableData.getFirst().size(); i++) {
            String currentString = tableData.getFirst().get(i);
            String spaceFill = " ".repeat(columnWidths.get(i) - currentString.length());
            System.out.printf(" ".repeat(cellPaddingX) + "%s" + spaceFill + " ".repeat(cellPaddingX) + "|", currentString);
        }

        printRowSeparator(cellPaddingX, columnWidths);

        // Print out every row after the header
        for (int i = 1; i < tableData.size(); i++) {
            System.out.println();
            ArrayList<String> currentRow = tableData.get(i);
            System.out.print("|");
            for (int j = 0; j < currentRow.size(); j++) {
                String currentString = currentRow.get(j);
                String spaceFill = " ".repeat(columnWidths.get(j) - currentString.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length());
                System.out.printf(" ".repeat(cellPaddingX) + "%s" + spaceFill + " ".repeat(cellPaddingX) + "|", currentString);
            }
        }
        printRowSeparator(cellPaddingX, columnWidths);
    }

    public static void printRowSeparator(int cellPaddingX, ArrayList<Integer> columnWidths) {
        // total width of all columns + # of total separators + cell padding of each column
        int totalCharacters = (cellPaddingX * 2 * columnWidths.size()) + (columnWidths.size() + 1);
        for (int width : columnWidths) {
            totalCharacters += width;
        }
        System.out.printf("\n" + "-".repeat(totalCharacters));
    }

    public static void printProgramHeaderEnglish() {
        System.out.println("-".repeat(100));
        System.out.println("-- Welcome to:");
        System.out.println("--              BODY MASS INDEX (BMI) Computation, CSC 215, English version");
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
//        a. Prompt the user to enter the user’s name.
//        b. Prompt the user to enter height in feet and inches (both integers).
//        c. Prompt the user to enter weight in pounds.
//        d. Calculate the BMI of the user and display a summary report.
//        e. Prompt our user to enter a low weight and a high weight in pounds.
//        f. Generate and display a table of BMI information for the entered weight range

        String name;

        // Height is stored in inches
        int height;
        double weight;
        double lowWeight;
        double highWeight;
        double BMI;
        String BMICategory;

        printProgramHeaderEnglish();

        name = getName();
        height = getHeightEnglish(name);
        weight = getWeightEnglish(name);
        BMI = calculateBMIEnglish(height, weight);
        BMICategory = getBMICategory(BMI);

        printSummary(name, BMI, BMICategory);

        // We need to combine this into one function getWeight, getLowWeight getHighWeight gets
        // Put into getWeight(String prompt, name);

        lowWeight = getLowWeight(name);
        highWeight = getHighWeight(name);

        printBMITable(weight, BMI, height, lowWeight, highWeight, 25, 2);

        printProgramFooter(name);
    }
}
