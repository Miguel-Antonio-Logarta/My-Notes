public class Wheels {
    private String tireMeasurements; // e.g., "225/45R17"
    private String tireName;         // e.g., "Michelin Pilot Sport"
    private String weatherType;      // e.g. "Summer", "Winter", "Rain", "All-Seasons"

    // Constructor
    public Wheels(String tireMeasurements, String tireName, String weatherType) {
        this.tireMeasurements = tireMeasurements;
        this.tireName = tireName;
        this.weatherType = weatherType;
    }

    // Method to get wheel specifications
    public String getWheelsSpecs() {
        return "Wheel Specifications:\n" +
                " - Tire Measurements: " + this.tireMeasurements + "\n" +
                " - Tire Name: " + this.tireName + "\n" +
                " - Weather Type: " + this.weatherType + " mm";
    }
}
