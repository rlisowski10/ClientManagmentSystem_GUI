package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class SearchView {

    // ============================================================
    // Member Variables
    // ============================================================

    private JPanel searchPanel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
    private JLabel searchClientsLabel;
    private JLabel selectSearchTypeLabel;

    private JRadioButton clientIDRadioButton;
    private JRadioButton lastNameRadioButton;
    private JRadioButton clientTypeRadioButton;

    private JLabel searchParameterLabel;
    private JTextField searchParameterTextField;
    private JButton searchButton;
    private JButton clearButton;

    // ============================================================
    // Constructors
    // ============================================================

    public SearchView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Accessors
    // ============================================================

    public JPanel getPanel() {
        return searchPanel;
    }

    public boolean clientIDRadioButtonStatus() {
        return clientIDRadioButton.isSelected();
    }

    public boolean lastNameRadioButtonStatus() {
        return lastNameRadioButton.isSelected();
    }

    public boolean clientTypeRadioButtonStatus() {
        return clientTypeRadioButton.isSelected();
    }

    public String getSearchParameterTextField() {
        return searchParameterTextField.getText();
    }

    public void setSearchParameterTextField(String searchParamter) {
        searchParameterTextField.setText(searchParamter);
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public void addSearchListener(ActionListener listenForSearchButton) {
        searchButton.addActionListener(listenForSearchButton);
    }

    public void addClearListener(ActionListener listenForClearButton) {
        clearButton.addActionListener(listenForClearButton);
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void setupPanel() {
        searchPanel = new JPanel();
        gbLayout = new GridBagLayout();
        searchPanel.setLayout(gbLayout);

        // Border panelBorder = BorderFactory.createLineBorder(Color.black);
        // searchPanel.setBorder(panelBorder);

        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(5, 5, 5, 5);
    }

    private void createControls() {
        searchClientsLabel = new JLabel("Search Clients");
        selectSearchTypeLabel = new JLabel("Select type of search to be performed:");

        clientIDRadioButton = new JRadioButton("Client ID");
        clientIDRadioButton.setSelected(true);
        clientIDRadioButton.setFocusPainted(false);
        lastNameRadioButton = new JRadioButton("Last Name");
        lastNameRadioButton.setFocusPainted(false);
        clientTypeRadioButton = new JRadioButton("Client Type");
        clientTypeRadioButton.setFocusPainted(false);

        ButtonGroup group = new ButtonGroup();
        group.add(clientIDRadioButton);
        group.add(lastNameRadioButton);
        group.add(clientTypeRadioButton);

        searchParameterLabel = new JLabel("Enter the search parameter below:");
        searchParameterTextField = new JTextField("", 23);
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
    }

    private void populatePanel() {
        addComponentToPanel(searchClientsLabel, 0, 0, 0, 15, 1, "West");
        addComponentToPanel(selectSearchTypeLabel, 0, 1, 0, 27, 2, "West");

        addComponentToPanel(clientIDRadioButton, 0, 2, 0, 0, 1, "West");
        addComponentToPanel(lastNameRadioButton, 0, 3, 0, 0, 1, "West");
        addComponentToPanel(clientTypeRadioButton, 0, 4, 0, 0, 1, "West");

        addComponentToPanel(searchParameterLabel, 0, 5, 0, 0, 2, "West");
        addComponentToPanel(searchParameterTextField, 0, 6, 0, 0, 2, "West");
        addComponentToPanel(searchButton, 0, 7, 0, 0, 1, "West");
        addComponentToPanel(clearButton, 1, 7, 0, 0, 1, "West");
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
        searchPanel.add(component);
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