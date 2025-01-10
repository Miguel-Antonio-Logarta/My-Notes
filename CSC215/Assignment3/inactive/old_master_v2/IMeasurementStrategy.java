/*
* Common methods between English and Imperial, but they do stuff a little differently
* getHeight()
* getWeight()
* printSummary()
* calculateBMI()
* printProgramHeader()
* */



public interface IMeasurementStrategy {
    public int getHeight(String name);
    public double getWeight(String name);
    public void printSummary(String name, double BMI, String BMICategory);
    public double calculateBMI(int height, int weight);
    public void printProgramHeader();
}
