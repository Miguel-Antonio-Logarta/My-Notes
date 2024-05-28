import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * Allows the user to select one or more items from a JList
 * component. Selected items are passed on to the save button's
 * Action so the item information can be saved.
 */
public class ItemSelectionHandler implements ListSelectionListener {
    private final JList<Item> list;
    private final SaveAction saveAction;
    public ItemSelectionHandler(JList<Item> list, SaveAction saveAction) {
        this.list = list;
        this.saveAction = saveAction;
    }

    /**
     * After changing the items selected in the list,
     * the save button Action is updated so only the
     * selected items are saved.
     */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            List<Item> selected = list.getSelectedValuesList();
            saveAction.updateSelectedItems(selected);
        }
    }
}
