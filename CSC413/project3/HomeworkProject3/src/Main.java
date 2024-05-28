import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        WeatherLog log = new WeatherLog();
        Random rand = new Random();

        for (int day = 1; day <= 31; day++) {
            // Generate random temperature and wind data for the month of December 2023.
            Date date = new Date("2023-12-" + day);
            Temperature temperature = new Temperature(rand.nextInt(35));
            Pressure pressure = new Pressure(rand.nextInt(2000));
            Humidity humidity = new Humidity(rand.nextInt(100));

            Speed windSpeed = new Speed(rand.nextInt(50));
            Direction windDirection = new Direction(rand.nextInt(360));
            WindVelocity windVelocity = new WindVelocity(windSpeed, windDirection);

            // Add the randomly generated record to the log
            WeatherRecord sample = new WeatherRecord(date, temperature, windVelocity, pressure, humidity);
            log.addRecord(sample);
        }

        log.save("SimulatedWeatherData.txt");
    }
}