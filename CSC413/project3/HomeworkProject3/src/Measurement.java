/**
 * Stores a floating point value
 * representing one data point.
 */
public class Measurement {
    private final float value;
    public Measurement(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
