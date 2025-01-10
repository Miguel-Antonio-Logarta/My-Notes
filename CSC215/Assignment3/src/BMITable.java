import java.util.ArrayList;

/**
 * BMITable is a class that generates and renders a table containing
 * weight, BMI, and BMI categories for the specified low and high range.
 * It renders automatically calculates and renders the table for you
 * on the console.
 */
public class BMITable {

    private int targetHeight;
    private double targetWeight;
    private double targetBMI;

    private final int NUM_COLUMNS = 3;
    private final Table bmiTable = new Table(true, 2);

    // ANSI Codes for highlighting LOW weight and HIGH weight
    private final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String RESET = "\033[0m";


    public BMITable() {
        ArrayList<String> tableHeaders = new ArrayList<>();
        tableHeaders.add("WEIGHT");
        tableHeaders.add("BMI");
        tableHeaders.add("WEIGHT STATUS");

        this.bmiTable.addRow(tableHeaders);
    }


    /**
     * Sets the user's BMI in the BMI table
     * When the table renders, it is indicated with "(this)"
     * @param height height in centimeters
     * @param weight weight in kilograms
     */
    public void setTargetBMI(int height, double weight) {
        this.targetHeight = height;
        this.targetWeight = weight;
        this.targetBMI = getBMIMetric(height, weight);
    }

    /**
     * Returns a BMI using the English measurement system
     * @param height height in inches
     * @param weight weight in pounds
     * @return BMI
     */
    public static double getBMIEnglish(int height, double weight) {
        return (weight / Math.pow(height, 2.0)) * 703.0;
    }

    /**
     * Returns a BMI using the Metric measurement system
     * @param height height in centimeters
     * @param weight weight in kilograms
     * @return BMI
     */
    public static double getBMIMetric(int height, double weight) {
        return (weight) / (Math.pow((double)height / (double)100, 2));
    }


    /**
     * Returns your BMI category from your BMI number
     * @param BMI Your BMI number
     * @return A string that says "Underweight", "Healthy Weight", "Overweight", or "Obesity"
     */
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

    /**
     * Generates n rows of bmi values ranging from lowWeight (kg) to highWeight (kg)
     * @param lowWeight lower weight limit (kg)
     * @param highWeight high weight limit (kg)
     * @param n number of rows to generate
     */
    public void generateTable(double lowWeight, double highWeight, int n, boolean metric) {
        // Preliminary Checks.
        if (this.targetBMI < 0 || lowWeight < 0 || highWeight < 0) {
            System.out.println("BMI, LOW weight, and HIGH weight cannot be negative!");
            return;
        } else if (lowWeight >= highWeight) {
            System.out.println("LOW weight cannot be higher than HIGH weight!");
            return;
        } else if (n < 2) {
            System.out.println("Not enough rows to print out a table");
            return;
        }


        // Calculate rows
        double step = (highWeight - lowWeight) / n;
        double currentWeight = lowWeight;
        for (int i = 0; i <= n; i++) {
            ArrayList<String> row = new ArrayList<>();

            // Weight
            // 2 digits of precision, right aligned numbers
            // 7 characters wide, to allow weight limit to be at most 9999.99 lbs
            String formattedRowWeight = Utilities.padLeft(String.format("%.2f", currentWeight), 7);
            row.add(formattedRowWeight);

            // BMI
            // 5 digits of precision, left aligned numbers. Don't show trailing zeroes
            double rowBMI;

            if (metric) {
                rowBMI = getBMIMetric(this.targetHeight, currentWeight);
            } else {
                rowBMI = getBMIEnglish(this.targetHeight, currentWeight);
            }

            String formattedRowBMI = Utilities.padRight(String.format("%.5f", rowBMI), 7);
            row.add(formattedRowBMI);

            // BMI category
            String BMICategory = getBMICategory(rowBMI);
            row.add(BMICategory);
            this.bmiTable.addRow(row);

            // Increment
            currentWeight += step;
        }
    }

    /**
     * Renders the BMI table onto the console.
     * Unlike a regular table, it adds your current BMI,
     * LOW BMI, and HIGH BMI indicators to the table
     */
    public void renderTable() {
        // Go through every row in the last column of the table to find the widest cell
        int widestCellLength = getMinimumColumnWidth(NUM_COLUMNS - 1);

        insertHighHighlight(widestCellLength);
        insertLowHighlight(widestCellLength);
        insertThisIndicator();

        this.bmiTable.renderTable();
    }

    private void insertHighHighlight(int widestCellLength) {
        // Get the last cell in the last column
        String highString = this.bmiTable.getTable().getLast().getLast();
        int spacesToAdd = widestCellLength - highString.length();

        highString =
                highString +
                " ".repeat(spacesToAdd + 1) +
                ANSI_YELLOW_BACKGROUND +
                ANSI_BLACK + "(HIGH)" +
                RESET;

        this.bmiTable.getTable().getLast().set(this.bmiTable.getTable().getLast().size() - 1, highString);
    }

    private void insertLowHighlight(int widestCellLength) {
        // Get last cell in the first column
        String lowString = this.bmiTable.getTable().get(1).getLast();
        int spacesToAdd = widestCellLength - lowString.length();

        lowString =
                lowString +
                " ".repeat(spacesToAdd + 1) +
                ANSI_YELLOW_BACKGROUND +
                ANSI_BLACK + "(LOW)" +
                RESET;

        this.bmiTable.getTable().get(1).set(this.bmiTable.getTable().get(1).size() - 1, lowString);
    }

    private void insertThisIndicator() {
        // Find the index of the row that has the closest weight to the user using binary search
        int low = 1;
        int high = this.bmiTable.size() - 1;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            mid = low + (high - low) / 2;
            double midWeight = Double.parseDouble(this.bmiTable.getTable().get(mid).getFirst());

            if (midWeight == this.targetWeight) {
                break;
            }

            if (midWeight < this.targetWeight) {
                // If x greater, ignore lower half
                low = mid + 1;
            } else {
                // If x is smaller, ignore upper half
                high = mid - 1;
            }
        }

        // Append (this) to last cell in the selected row
        String thisString = this.bmiTable.getTable().get(mid).getLast() + " (this)";
        this.bmiTable.getTable().get(mid).set(this.bmiTable.getTable().get(mid).size() - 1, thisString);
    }

    private int getMinimumColumnWidth(int column) {
        int widestCellLength = 0;

        for (ArrayList<String> row : this.bmiTable.getTable()) {
            int cellWidth = row.get(column).length();
            if (cellWidth > widestCellLength) {
                widestCellLength = cellWidth;
            }
        }

        return widestCellLength;
    }


}
