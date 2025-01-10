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

    private int numRows;
    private double step;
    private final Table bmiTable = new Table(true, 2);

    // Highlight LOW weight, user's BMI, and HIGH weight
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
    public void generateTable(double lowWeight, double highWeight, int n) {
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
            double rowBMI = BMITable.getBMIMetric(this.targetHeight, currentWeight);
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
        // Ugh the code is so ugly. I think I'll just hardcode the indices later since
        // We know that bmiTable will always be an n x 3 matrix

        // Go through every row in the last column of the table to find the widest cell
        int widestCellLength = 0;
        for (int i = 1; i < this.bmiTable.size(); i++) {
            int cellWidth = this.bmiTable.get(i, this.bmiTable.get(i).size() - 1).length();
            if (cellWidth > widestCellLength) {
                widestCellLength = cellWidth;
            }
        }

        // Insert HIGH into the last row of the table
        // Get the last element
        int lastRow = this.bmiTable.size() - 1;
        int lastCell = this.bmiTable.get(lastRow).size() - 1;
        String highString = this.bmiTable.get(lastRow, lastCell);
        int spacesToAdd = widestCellLength - highString.length();

        highString = highString + " ".repeat(spacesToAdd + 1) +  ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "(HIGH)" + RESET;
        this.bmiTable.set(lastRow, lastCell, highString);

        // Insert LOW into the first row of the table
        int firstRow = 1;
        lastCell = this.bmiTable.get(firstRow).size() - 1;

        String lowString = this.bmiTable.get(firstRow, lastCell);
        spacesToAdd = widestCellLength - lowString.length();

        lowString = lowString + " ".repeat(spacesToAdd + 1) +  ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "(LOW)" + RESET;
        this.bmiTable.set(firstRow, lastCell, lowString);

        // Set (this) in the middle of the table
        // Find the index of the row that has the closest weight to the user (Binary search)
        int low = 1;
        int high = this.bmiTable.size() - 1;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (Double.parseDouble(this.bmiTable.get(mid, 0)) == this.targetWeight) {
                break;
            }

            if (Double.parseDouble(this.bmiTable.get(mid, 0)) < this.targetWeight) {
                // If x greater, ignore left half
                low = mid + 1;
            } else {
                // If x is smaller, ignore right half
                high = mid - 1;
            }
        }

        String thisString = this.bmiTable.get(mid, this.bmiTable.get(mid).size() - 1) + " (this)";
        this.bmiTable.set(mid, this.bmiTable.get(mid).size() - 1, thisString);

        this.bmiTable.renderTable();
    }
}
