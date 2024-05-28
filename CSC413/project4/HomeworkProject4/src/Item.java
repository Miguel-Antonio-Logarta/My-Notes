import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Stores the name and price of an item
 * which can be purchased.
 */
public class Item {
    private final String name;
    private final float price;

    public Item(String name, double price) {
        this.name = name;
        this.price = (float) price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    /**
     * We would like to display prices with only 2 decimal places.
     * This function creates a String containing the price in the
     * desired format.
     *
     * @return String containing the price truncated to at most 2 decimal places.
     */
    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(price);
    }

    /**
     * The list displaying the available items should
     * show the item name. By default, an object's toString()
     * method would return an unreadable reference. We
     * can override the toString() method to return the
     * desired text, instead of the object reference.
     *
     * @return Name of the Item
     */
    @Override
    public String toString() {
        return getName();
    }
}
