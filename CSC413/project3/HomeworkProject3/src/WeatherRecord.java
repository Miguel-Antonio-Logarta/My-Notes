/**
 * Stores measurements of temperature, wind velocity,
 * atmospheric pressure, and humidity sampled on a given day.
 */
public class WeatherRecord {
    private final Date date;
    private final Temperature temperature;
    private final WindVelocity windVelocity;
    private final Pressure pressure;
    private final Humidity humidity;

    public WeatherRecord(Date date, Temperature temperature, WindVelocity windVelocity, Pressure pressure, Humidity humidity) {
        this.date = date;
        this.temperature = temperature;
        this.windVelocity = windVelocity;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Date getDate() {
        return date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public WindVelocity getWindVelocity() {
        return windVelocity;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Pressure getPressure() {
        return pressure;
    }
}
