import javax.swing.*;
import java.util.ArrayList;

/**
 * Graphical User Interface for a billing program,
 * which allows a user to select which items were
 * purchased and save their name and price to a
 * CSV file.
 */
public class LogbookUI extends JFrame {
    public LogbookUI() {
        super("My Graphical UI");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create a content pane
        JPanel pane = new JPanel();
        BoxLayout layout = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(layout);

        JLabel label = new JLabel("Select items from list. Hold Control or Shift to select multiple.");

        // Create save button
        SaveAction saveButtonAction = new SaveAction("itemsPurchased.csv");
        JButton saveButton = new JButton(saveButtonAction);

        // Create the list of items which can be purchased
        Item[] availableItems = getAvailableItems();
        JList<Item> list = new JList<>(availableItems);
        JScrollPane listPane = new JScrollPane(list);

        ListSelectionModel listModel = list.getSelectionModel();
        listModel.addListSelectionListener(new ItemSelectionHandler(list, saveButtonAction));
        listModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add components to the content pane
        pane.add(label);
        pane.add(listPane);
        pane.add(saveButton);

        this.setContentPane(pane);
        this.pack();
        this.setVisible(true);
    }

    private Item[] getAvailableItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Chocolate Bar", 2.50));
        items.add(new Item("Toast", 3.75));
        items.add(new Item("Coffee", 2.40));
        items.add(new Item("Sandwich", 7.00));
        items.add(new Item("Soup", 5.25));

        return items.toArray(new Item[0]);
    }
}
