import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Stores daily weather records and saves
 * the data to a CSV file.
 */
public class WeatherLog {
    private final ArrayList<WeatherRecord> records;

    public WeatherLog() {
        records = new ArrayList<>();
    }

    /**
     * Store a new day's weather record. Records are
     * currently not checked for duplicate dates.
     *
     * @param record Weather record to store.
     */
    public void addRecord(WeatherRecord record) {
        records.add(record);
    }

    /**
     * Save all stored weather records to a CSV file.
     *
     * @param path  CSV file path.
     */
    public void save(String path) {
        try {
            System.out.println("Saving weather data to " + path + "...");

            // Write column (variable) names to start a CSV file
            PrintWriter file = new PrintWriter(path);
            file.println("date,temperature,wind_speed,wind_direction,pressure,humidity");

            for (WeatherRecord record : records) {
                // Get data for each day the weather was recorded
                Date date = record.getDate();
                Temperature temperature = record.getTemperature();
                WindVelocity wind = record.getWindVelocity();
                Pressure pressure = record.getPressure();
                Humidity humidity = record.getHumidity();

                file.print(date.toString() + ",");
                file.print(temperature.getValue() + ",");
                file.print(wind.getSpeed().getValue() + ",");
                file.print(wind.getDirection().getValue() + ",");
                file.print(pressure.getValue() + ",");
                file.println(humidity.getValue());
            }

            file.close();
            System.out.println("Saved " + records.size() + " weather records.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
