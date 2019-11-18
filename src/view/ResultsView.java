package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class contains the panel and all componenets that make up the results
 * view functionality of the user interface. Components are added to the panel,
 * and are laid-out using a GridBagLayout.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ResultsView {

    // ============================================================
    // Member Variables
    // ============================================================

    private JPanel resultsPanel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
    private JLabel searchResultsLabel;
    private JTable resultsTable;
    private JScrollPane resultsTextAreaScrollPane;
    private DefaultTableModel defaultTableModel;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * The constructor for the results view panel.
     */
    public ResultsView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Accessors
    // ============================================================

    /**
     * Gets the user-interface results panel.
     * 
     * @return JPanel the user-interface results panel.
     */
    public JPanel getResultsPanel() {
        return resultsPanel;
    }

    /**
     * Gets the client ID associated with the selected table row.
     * 
     * @return String The client ID for the selected row.
     */
    public String getSelectedTableRowClientID() {
        String selectedClientID = "";
        int selectedRowIndex = resultsTable.getSelectedRow();
        int columnIndex = 0;

        if (resultsTable.getSelectedRow() != -1) {
            // Assigns the client ID value form the table to the variable.
            selectedClientID = resultsTable.getValueAt(selectedRowIndex, columnIndex).toString();
        }

        return selectedClientID;
    }

    // ============================================================
    // Listeners
    // ============================================================

    /**
     * Adds a list selection listener to the results table.
     * 
     * @param listenForTableSelection A list selection listener for the results
     *                                table.
     */
    public void addTableSelectionListener(ListSelectionListener listenForTableSelection) {
        resultsTable.getSelectionModel().addListSelectionListener(listenForTableSelection);
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    /**
     * Updates the results table with an arraylist of strings pulled from the
     * database.
     * 
     * @param clientList A list of strings taken from the database.
     */
    public void updateResultsTable(ArrayList<String[]> clientList) {
        // Removes all existing items from the results table.
        defaultTableModel.setRowCount(0);

        // Populates the results table with the database results.
        for (String[] client : clientList) {
            defaultTableModel.addRow(client);
        }

        // Notifies the table that the data that it is displaying has changed.
        defaultTableModel.fireTableDataChanged();
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Sets up the panel by creating the Panel, GridBagLayout, and
     * GridBagConstraints objects.
     */
    private void setupPanel() {
        resultsPanel = new JPanel();
        gbLayout = new GridBagLayout();
        resultsPanel.setLayout(gbLayout);

        // Adding insets adds a defined spacing between each UI control element.
        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(5, 5, 5, 5);
    }

    /**
     * Creates the user-interface controls for the results panel layout.
     */
    private void createControls() {
        searchResultsLabel = new JLabel("Search Results:");

        // Sets the column headings names for the results table.
        String columnHeadings[] = { "ID", "Name", "Type" };
        defaultTableModel = new DefaultTableModel(columnHeadings, 0);
        resultsTable = new JTable(defaultTableModel);

        // Sets the size of the columns and results text area.
        resultsTable.getColumnModel().getColumn(1).setPreferredWidth(700);
        resultsTextAreaScrollPane = new JScrollPane(resultsTable);
        Dimension dim = new DimensionUIResource(160, 150);
        resultsTextAreaScrollPane.setPreferredSize(dim);
    }

    /**
     * Adds all of the user-interface components to the Results panel, while
     * specifying their grid position, padding, grid width, and position within the
     * grid element.
     */
    private void populatePanel() {
        addComponentToPanel(searchResultsLabel, 0, 0, 0, 0, 1, "West");
        addComponentToPanel(resultsTextAreaScrollPane, 0, 1, 100, 0, 1, "West");
    }

    /**
     * Adds a component to the Results panel.
     * 
     * @param component Component to be added to the results panel.
     * @param gridXPos  Position in the GridBagLayout in the x-axis.
     * @param gridYPos  Position in the GridBagLayout in the y-axis.
     * @param iPadX     Padding in the x-axis.
     * @param iPadY     Padding in the y-axis.
     * @param gridwidth The number of grid columns that the control can lay across.
     * @param position  The anchor position within the grid element.
     */
    private void addComponentToPanel(Component component, int gridXPos, int gridYPos, int iPadX, int iPadY,
            int gridwidth, String position) {
        gbConstraints.gridx = gridXPos;
        gbConstraints.gridy = gridYPos;

        gbConstraints.ipadx = iPadX;
        gbConstraints.ipady = iPadY;
        gbConstraints.gridwidth = gridwidth;
        gbConstraints.anchor = setPosition(position);

        gbLayout.setConstraints(component, gbConstraints);
        resultsPanel.add(component);
    }

    /**
     * Returns an anchor position within the grid element based on a text
     * description.
     * 
     * @param position The text description of the control's position within the
     *                 grid element.
     * @return int An int representation of the control's position within the grid
     *         element.
     */
    private int setPosition(String position) {
        switch (position) {
        case "West":
            return GridBagConstraints.WEST;
        case "Center":
            return GridBagConstraints.CENTER;
        case "East":
            return GridBagConstraints.EAST;
        default:
            return GridBagConstraints.WEST;
        }
    }
}