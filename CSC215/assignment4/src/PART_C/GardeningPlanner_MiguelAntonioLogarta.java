package PART_C;

import java.util.InputMismatchException;
import java.util.Scanner;



public class GardeningPlanner_MiguelAntonioLogarta {
    // Formatting and Space Options
    public static final int COLUMN_WIDTH = 14;
    public static final int NUM_COLUMNS = 6;
    public static final int SPACE_BETWEEN = 1;
    public static final int PROGRAM_WIDTH = NUM_COLUMNS * COLUMN_WIDTH + ((NUM_COLUMNS - 1) * SPACE_BETWEEN);

    // Text and Background Colors
    public static final String ANSI_YELLOW_BACKGROUND = "\u001b[43;1m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String RESET = "\033[0m";

    // Data to calculate for our table
    public static class PlantData {
        public int[] plantGrowth;
        public int[] plantHeight;
        public int maxHeight;
    }

    /**
     * Strips ANSI color codes from the string. Useful when trying to get the length of the string without hidden
     * characters.
     * @param s a string
     * @return a string with ansi color codes removed
     */
    public static String stripAnsiCodes(String s) {
        return s.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "");
    }

    public static String padRight(String s, int totalLength) {
        return String.format("%s" + " ".repeat(totalLength - stripAnsiCodes(s).length()), s);
    }

    public static int getInteger(String prompt, String errorMsg) {
        System.out.print(prompt);
        while (true) {
            Scanner sc = new Scanner(System.in);

            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(errorMsg);
                System.out.print(prompt);
            }
        }
    }

    public static void printProgramHeader(int width) {
        System.out.println("-".repeat(width));
        System.out.println("Welcome to the CSC 215 Gardening Planner!");
        System.out.println("-".repeat(width));
    }

    public static void printTableHeaders(String[] headers, int width) {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(headers[i] + " ".repeat(COLUMN_WIDTH - headers[i].length()));
        }
        System.out.println();
    }

    public static void printTableSeparators(int cols, int tableWidth) {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print("-".repeat(COLUMN_WIDTH));
        }
        System.out.println();
    }

    public static void printTable(String[] headers, String[] months, int[] avgTemp, int[] avgRain, PlantData plantData) {
        System.out.println();

        printTableSeparators(NUM_COLUMNS, PROGRAM_WIDTH);
        printTableHeaders(headers, NUM_COLUMNS);
        printTableData(months, avgTemp, avgRain, plantData.plantGrowth, plantData.plantHeight, plantData.maxHeight);
        printTableSeparators(NUM_COLUMNS, PROGRAM_WIDTH);
    }

    public static void printTableData(String[] months, int[] avgTemp, int[] avgRain, int[] plantGrowth, int[] plantHeight, int maxHeight) {
        // Print out table data
        for (int i = 0; i < avgTemp.length; i++) {
            // Index
            System.out.printf(padRight(String.format("%2d", i), COLUMN_WIDTH + SPACE_BETWEEN));

            // Month
            System.out.printf(padRight(months[i], COLUMN_WIDTH + SPACE_BETWEEN));

            // Temperature
            System.out.printf(padRight(String.format("%2d", avgTemp[i]), COLUMN_WIDTH + SPACE_BETWEEN));

            // Rainfall
            System.out.printf(padRight(String.format("%d", avgRain[i]), COLUMN_WIDTH + SPACE_BETWEEN));

            // Plant Growth
            System.out.printf(padRight(String.format("%+2d", plantGrowth[i]), COLUMN_WIDTH + SPACE_BETWEEN));

            // Plant Height
            String heightString = String.format("%d", plantHeight[i]);
            if (plantHeight[i] == maxHeight) {
                heightString +=
                    "    " +
                    ANSI_YELLOW_BACKGROUND +
                    ANSI_BLACK + "MAX" +
                    RESET;
            }

            System.out.printf(padRight(heightString, COLUMN_WIDTH + SPACE_BETWEEN));

            System.out.println();
        }
    }

    public static PlantData calculatePlantData(int[] avgTemp, int[] avgRain, int minTemp, int maxTemp, int minRainfall) {
        PlantData plantData = new PlantData();
        plantData.plantGrowth = new int[avgTemp.length];
        plantData.plantHeight = new int[avgTemp.length];
        plantData.maxHeight = 0;

        // Calculate plant growth and height
        for (int i = 0; i < avgTemp.length; i++) {
            // Calculating plant growth
            if (avgTemp[i] < minTemp || avgTemp[i] > maxTemp) {
                plantData.plantGrowth[i] = -1;
            } else {
                plantData.plantGrowth[i] = avgRain[i] - minRainfall;
            }

            // Calculating plant height
            if (i == 0) {
                plantData.plantHeight[i] = plantData.plantGrowth[i];
            } else {
                plantData.plantHeight[i] = plantData.plantHeight[i-1] + plantData.plantGrowth[i];
            }

            if (plantData.plantHeight[i] < 0) {
                plantData.plantHeight[i] = 0;
            }

            // Check if it's the tallest plant height so far
            if (plantData.plantHeight[i] > plantData.maxHeight) {
                plantData.maxHeight = plantData.plantHeight[i];
            }
        }

        return plantData;
    }

    public static void main(String[] args) {
        // Predefined data
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int[] avgTemp = {46, 48, 49, 50, 51, 53, 54, 55, 56, 55, 51, 47};
        int[] avgRain = {5, 3, 3, 1, 1, 0, 0, 0, 0, 1, 3, 4};
        String[] headers = {"INDEX", "MONTH", "TEMPERATURE", "RAINFALL", "PLANT GROWTH", "PLANT HEIGHT"};

        // Input from the user
        int maxTemp;
        int minTemp;
        int minRainfall;

        // Data to calculate
        PlantData plantData;

        if (avgTemp.length != avgRain.length) {
            System.out.println("Data for average temperatures and average rainfall is incomplete!");
        }

        printProgramHeader(PROGRAM_WIDTH);
        minTemp = getInteger("- Enter minimum temperature for plant: ", "Your input was invalid, please enter a number\n");
        maxTemp = getInteger("- Enter maximum temperature for plant: ", "Your input was invalid, please enter a number\n");
        minRainfall = getInteger("- Enter minimum rainfall for plant: ", "Your input was invalid, please enter a number\n");
        System.out.println("-".repeat(PROGRAM_WIDTH));

        plantData = calculatePlantData(avgTemp, avgRain, minTemp, maxTemp, minRainfall);

        printTable(headers, months, avgTemp, avgRain, plantData);
    }
}
