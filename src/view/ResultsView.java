package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionListener;

public class ResultsView {

    // ============================================================
    // Member Variables
    // ============================================================

    private JPanel resultsPanel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
    private JLabel searchResultsLabel;
    private JList<String> resultsList;
    private JScrollPane resultsTextAreaScrollPane;

    // ============================================================
    // Constructors
    // ============================================================

    public ResultsView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public JPanel getPanel() {
        return resultsPanel;
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
        String labels[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        resultsList = new JList<String>(labels);
        //resultsList = new JList<String>();

        resultsTextAreaScrollPane = new JScrollPane(resultsList);
    }

    private void populatePanel() {
        addComponentToPanel(searchResultsLabel, 0, 0, 0, 0, 1, "West");
        addComponentToPanel(resultsTextAreaScrollPane, 0, 1, 230, 0, 1, "West");
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