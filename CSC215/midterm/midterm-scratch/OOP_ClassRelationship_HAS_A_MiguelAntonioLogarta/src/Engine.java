public class Engine {
    private double displacement; // Displacement in liters (e.g. 4.0L)
    private int cylinders;       // Number of cylinders (e.g., 4, 6, 8)
    private String fuelType;     // Type of fuel with fuel grade (e.g., "Gasoline", "Diesel", "Electric" then 87, 89, 91, E85)
    private int horsepower;      // Horsepower (e.g., 200)

    // Constructor
    public Engine(double displacement, int cylinders, String fuelType, int horsepower) {
        this.displacement = displacement;
        this.cylinders = cylinders;
        this.fuelType = fuelType;
        this.horsepower = horsepower;
    }

    // Method to get engine specifications as a formatted string
    public String getEngineSpecs() {
        return "Engine Specifications:\n" +
                " - Displacement: " + this.displacement + "L\n" +
                " - Cylinders: " + this.cylinders + "\n" +
                " - Fuel Type: " + this.fuelType + "\n" +
                " - Horsepower: " + this.horsepower + " hp";
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}
