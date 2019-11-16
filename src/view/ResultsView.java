package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public ResultsView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Accessors
    // ============================================================

    public JPanel getPanel() {
        return resultsPanel;
    }

    // ============================================================
    // Action Listeners
    // ============================================================

    public void addTableSelectionListener(ListSelectionListener listenForTableSelection) {
        resultsTable.getSelectionModel().addListSelectionListener(listenForTableSelection);
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public void updateResultsTable(ArrayList<String[]> clientList) {
        for (String[] client : clientList) {
            defaultTableModel.addRow(client);
        }
        
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void setupPanel() {
        resultsPanel = new JPanel();
        gbLayout = new GridBagLayout();
        resultsPanel.setLayout(gbLayout);

        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(5, 5, 5, 5);
    }

    private void createControls() {
        searchResultsLabel = new JLabel("Search Results:");

        String columnHeadings[] = { "ID", "Name", "Type" };

        defaultTableModel = new DefaultTableModel(columnHeadings, 0);
        resultsTable = new JTable(defaultTableModel);
        resultsTable.getColumnModel().getColumn(1).setPreferredWidth(700);

        resultsTextAreaScrollPane = new JScrollPane(resultsTable);
        Dimension dim = new DimensionUIResource(160, 150);
        resultsTextAreaScrollPane.setPreferredSize(dim);
    }

    private void populatePanel() {
        addComponentToPanel(searchResultsLabel, 0, 0, 0, 0, 1, "West");
        addComponentToPanel(resultsTextAreaScrollPane, 0, 1, 100, 0, 1, "West");
    }

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