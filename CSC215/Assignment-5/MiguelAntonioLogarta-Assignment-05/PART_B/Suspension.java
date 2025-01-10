public class Suspension {
    private String name;
    private String suspensionType;  // Mcpherson, double wishbone, etc...
    private String springType;      // Type of spring, e.g., "coil", "leaf", "air"
    private String drivetrainType;  // FWD, RWD, AWD
    
    // Constructor
    public Suspension(String name, String suspensionType, String springType, String drivetrainType) {
        this.name = name;
        this.suspensionType = suspensionType;
        this.springType = springType;
        this.drivetrainType = drivetrainType;
    }

    // Method to get suspension specifications
    public String getSuspensionSpecs() {
        return "Suspension Specifications:\n" +
                " - Suspension Name: " + name + "\n" +
                " - Suspension Type: " + suspensionType + "\n" +
                " - Spring Type: " + springType + "\n" +
                " - Drive Train: " + drivetrainType;
    }
}
