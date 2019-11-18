package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class contains the panel and all componenets that make up the search
 * functionality of the user interface. Components are added to the panel, and
 * are laid-out using a GridBagLayout.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
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

    /**
     * The constructor for the search view panel.
     */
    public SearchView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Accessors
    // ============================================================

    /**
     * Gets the user-interface search panel.
     * 
     * @return JPanel The user-interface search panel.
     */
    public JPanel getPanel() {
        return searchPanel;
    }

    /**
     * Returns a boolean that indicates whether the client ID radio button is
     * active.
     * 
     * @return boolean Indicates whether the client ID radio button is active.
     */
    public boolean clientIDRadioButtonStatus() {
        return clientIDRadioButton.isSelected();
    }

    /**
     * Returns a boolean that indicates whether the last name radio button is
     * active.
     * 
     * @return boolean Indicates whether the last name radio button is active.
     */
    public boolean lastNameRadioButtonStatus() {
        return lastNameRadioButton.isSelected();
    }

    /**
     * Returns a boolean that indicates whether the client type radio button is
     * active.
     * 
     * @return boolean Indicates whether the client type radio button is active.
     */
    public boolean clientTypeRadioButtonStatus() {
        return clientTypeRadioButton.isSelected();
    }

    /**
     * Gets the user-entered search parameter text.
     * 
     * @return String The user-entered search parameter text.
     */
    public String getSearchParameterTextField() {
        return searchParameterTextField.getText();
    }

    /**
     * Sets the search parameter text field to a String.
     * 
     * @param searchParameter The text to display in the search parameter text
     *                        field.
     */
    public void setSearchParameterTextField(String searchParameter) {
        searchParameterTextField.setText(searchParameter);
    }

    // ============================================================
    // Listeners
    // ============================================================

    /**
     * Adds an action listener to the search button.
     * 
     * @param listenForSearchButton An action listener for the search button.
     */
    public void addSearchListener(ActionListener listenForSearchButton) {
        searchButton.addActionListener(listenForSearchButton);
    }

    /**
     * Adds an action listener to the clear button.
     * 
     * @param listenForClearButton An action listener for the clear button.
     */
    public void addClearListener(ActionListener listenForClearButton) {
        clearButton.addActionListener(listenForClearButton);
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Sets up the panel by creating the Panel, GridBagLayout, and
     * GridBagConstraints objects.
     */
    private void setupPanel() {
        searchPanel = new JPanel();
        gbLayout = new GridBagLayout();
        searchPanel.setLayout(gbLayout);

        // Adding insets adds a defined spacing between each UI control element.
        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(5, 5, 5, 5);
    }

    /**
     * Creates the user-interface controls for the search panel layout.
     */
    private void createControls() {
        searchClientsLabel = new JLabel("Search Clients");
        selectSearchTypeLabel = new JLabel("Select type of search to be performed:");

        // Creates the radio buttons and assigns them to a group of buttons.
        clientIDRadioButton = new JRadioButton("Client ID");
        clientIDRadioButton.setSelected(true);
        clientIDRadioButton.setFocusPainted(false);
        lastNameRadioButton = new JRadioButton("Last Name");
        lastNameRadioButton.setFocusPainted(false);
        clientTypeRadioButton = new JRadioButton("Client Type");
        clientTypeRadioButton.setFocusPainted(false);

        // Having a button group provides single-selection functionality.
        ButtonGroup group = new ButtonGroup();
        group.add(clientIDRadioButton);
        group.add(lastNameRadioButton);
        group.add(clientTypeRadioButton);

        searchParameterLabel = new JLabel("Enter the search parameter below:");
        searchParameterTextField = new JTextField("", 23);
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
    }

    /**
     * Adds all of the user-interface components to the Search panel, while
     * specifying their grid position, padding, grid width, and position within the
     * grid element.
     */
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

    /**
     * Adds a component to the Search panel.
     * 
     * @param component Component to be added to the search panel.
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
        searchPanel.add(component);
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