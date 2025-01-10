class BMI_CSC215_English_MiguelLogarta {

    public static void runBMIMetric() {
        String name;
        int height;
        double weight;
        double lowWeight;
        double highWeight;
        double BMI;
        String BMICategory;
        BMITable bmiTable = new BMITable();

//        printProgramHeaderEnglish();
        BMICommon.printProgramHeaderEnglish();
        name = BMICommon.getName();
        height = BMICommon.getHeightEnglish(name);
        weight = BMICommon.getWeight(name,
                String.format("Please enter weight in pounds for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        BMI = BMICommon.calculateBMIMetric(height, weight);
        BMICategory = BMICommon.getBMICategory(BMI);

        BMICommon.printSummary(name, BMI, BMICategory);

        lowWeight = BMICommon.getWeight(name,
                String.format("Please enter a LOW weight in pounds for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );
        highWeight = BMICommon.getWeight(name,
                String.format("Please enter a HIGH weight in pounds for %s: ", name),
                "Your input was invalid. Please enter a number (decimals allowed)"
        );

        bmiTable.setTargetBMI(height, weight);
        bmiTable.generateTable(lowWeight, highWeight, 25);
        bmiTable.renderTable();

        BMICommon.printProgramFooter(name);
    }
}