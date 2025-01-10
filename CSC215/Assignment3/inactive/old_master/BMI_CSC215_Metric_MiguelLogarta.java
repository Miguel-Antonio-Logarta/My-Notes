import java.text.SimpleDateFormat;
import java.util.*;

class BMI_CSC215_Metric_MiguelLogarta {

    public static void runBMIMetric() {
        String name;
        int height;
        double weight;
        double lowWeight;
        double highWeight;
        double BMI;
        String BMICategory;
        BMITable bmiTable = new BMITable();


        name = BMICommon.getName();
        height = BMICommon.getHeightMetric(name);
        weight = BMICommon.getWeight(name,
                String.format("Please enter weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        BMI = BMICommon.calculateBMIMetric(height, weight);
        BMICategory = BMICommon.getBMICategory(BMI);

        BMICommon.printSummary(name, BMI, BMICategory);

        lowWeight = BMICommon.getWeight(name,
                String.format("Please enter a LOW weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        highWeight = BMICommon.getWeight(name,
                String.format("Please enter a HIGH weight in kilograms for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );

        bmiTable.setTargetBMI(height, weight);
        bmiTable.generateTable(lowWeight, highWeight, 25);
        bmiTable.renderTable();

        BMICommon.printProgramFooter(name);
    }
}