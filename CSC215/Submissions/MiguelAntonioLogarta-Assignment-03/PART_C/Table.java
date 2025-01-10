import java.util.ArrayList;

/**
 * A data structure that is equivalent to a table.
 * It has support for rendering the table on the console.
 * Automatically takes care of column widths and has options
 * to include a header and set horizontal cell padding.
 */
public class Table {
    private final ArrayList<ArrayList<String>> tableData = new ArrayList<>();
    private final ArrayList<Integer> columnWidths = new ArrayList<>();
    private int cellPaddingX = 0;
    private boolean useTableHeaders = false;

    public Table(boolean useTableHeaders, int cellPaddingX) {
        this.useTableHeaders = useTableHeaders;
        this.cellPaddingX = cellPaddingX;
    }

    public int getCellPaddingX() {
        return this.cellPaddingX;
    }

    public void setCellPaddingX(int cellPaddingX) {
        this.cellPaddingX = cellPaddingX;
    }

    public boolean getUseTableHeaders() {
        return this.useTableHeaders;
    }

    public void setUseTableHeaders(boolean useTableHeaders) {
        this.useTableHeaders = useTableHeaders;
    }

    public void addRow(ArrayList<String> row) {
        this.tableData.add(row);
    }

    public ArrayList<ArrayList<String>> get() {
        return this.tableData;
    }

    public ArrayList<String> get(int i) {
        return this.tableData.get(i);
    }

    public String get(int i, int j) {
        return this.tableData.get(i).get(j);
    }

    public void set(int i, ArrayList<String> e) {
        this.tableData.set(i, e);
    }

    public void set(int i, int j, String e) {
        this.tableData.get(i).set(j, e);
    }

    public int size() {
        return this.tableData.size();
    }

    public ArrayList<Integer> getColumnWidths() {
        return this.columnWidths;
    }

    public void renderTable() {
        // Make sure columns can fit the content inside of them
        calculateColumnWidths();

        printRowSeparator();

        // Print Header
        if (this.useTableHeaders) {
            System.out.print("\n|");
            // Print each cell, pad the data with spaces to align them
            for (int i = 0; i < this.tableData.getFirst().size(); i++) {
                String currentString = this.tableData.getFirst().get(i);
                String spaceFill = " ".repeat(this.columnWidths.get(i) - currentString.length());
                System.out.printf(" ".repeat(this.cellPaddingX) + "%s" + spaceFill + " ".repeat(this.cellPaddingX) + "|", currentString);
            }

            printRowSeparator();
        }


        // Print the rest of the table
        for (int i = this.useTableHeaders ? 1 : 0; i < this.tableData.size(); i++) {
            System.out.println();
            ArrayList<String> currentRow = this.tableData.get(i);
            System.out.print("|");
            for (int j = 0; j < currentRow.size(); j++) {
                String currentString = currentRow.get(j);
                String spaceFill = " ".repeat(this.columnWidths.get(j) - Utilities.stripAnsiCodes(currentString).length());
                System.out.printf(" ".repeat(this.cellPaddingX) + "%s" + spaceFill + " ".repeat(this.cellPaddingX) + "|", currentString);
            }
        }

        printRowSeparator();
    }

    public void calculateColumnWidths() {
        // Set the column width to be to the maximum length of the string in that column
        for (int i = 0; i < this.tableData.size(); i++) {
            for (int j = 0; j < this.tableData.get(i).size(); j++) {
                // If column width hasn't been set yet
                if (j > this.columnWidths.size() - 1) {
                    this.columnWidths.add(Utilities.stripAnsiCodes(this.tableData.get(i).get(j)).length());
                } else if (this.tableData.get(i).get(j).length() > this.columnWidths.get(j)) {
                    this.columnWidths.set(j, Utilities.stripAnsiCodes(this.tableData.get(i).get(j)).length());
                }
            }
        }
    }

    private void printRowSeparator() {
        // total width of all columns + # of total separators + cell padding of each column
        int totalCharacters = (this.cellPaddingX * 2 * this.columnWidths.size()) + (this.columnWidths.size() + 1);
        for (int width : this.columnWidths) {
            totalCharacters += width;
        }
        System.out.printf("\n" + "-".repeat(totalCharacters));
    }
}
