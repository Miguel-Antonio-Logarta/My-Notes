import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves the selected item's name and price to
 * a CSV file.
 */
public class SaveAction extends AbstractAction {
    private final ArrayList<Item> selectedItems;
    private final String filePath;

    public SaveAction(String filePath) {
        // Set button text
        super("Save Selected Items");

        selectedItems = new ArrayList<>();
        this.filePath = filePath;
    }

    public void updateSelectedItems(List<Item> items) {
        selectedItems.clear();
        selectedItems.addAll(items);
    }

    /**
     * When button is pressed, the selected items should
     * be saved to a CSV file. The file path is given
     * in the constructor.
     */
    public void actionPerformed(ActionEvent e) {
        // TODO: Save the name and price of each selected Item to a CSV file.
    }
}
