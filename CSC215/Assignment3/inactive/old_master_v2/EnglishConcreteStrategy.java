public class EnglishConcreteStrategy implements IMeasurementStrategy {

    /**
     * @param name 
     * @return
     */
    @Override
    public int getHeight(String name) {
        return 0;
    }

    /**
     * @param name 
     * @return
     */
    @Override
    public double getWeight(String name) {
        return 0;
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
