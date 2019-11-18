package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class contains the panel and all componenets that make up the individual
 * client view functionality of the user interface. Components are added to the
 * panel, and are laid-out using a GridBagLayout.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientView {

    // ============================================================
    // Member Variables
    // ============================================================

    private JPanel clientPanel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
    private JLabel clientInfoLabel;

    private JLabel clientIDLabel;
    private JTextField clientIDTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel postalCodeLabel;
    private JTextField postalCodeTextField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JLabel clientTypeLabel;
    private JComboBox<String> clientTypeComboBox;

    private JButton newButton;
    private JButton saveButton;
    private JButton deleteButton;

    private JLabel errorLabel;
    private InputVerifier clientVerification;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * The constructor for the client view panel.
     */
    public ClientView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Accessors
    // ============================================================

    /**
     * Gets the user-interface client panel.
     * 
     * @return JPanel The user-interface client panel.
     */
    public JPanel getPanel() {
        return clientPanel;
    }

    /**
     * Sets the client ID text field with the client ID.
     * 
     * @param clientID The client ID for the client.
     */
    public void setClientIDTextField(String clientID) {
        clientIDTextField.setText(clientID);
    }

    /**
     * Gets the client ID from the client ID text field.
     * 
     * @return String The client ID.
     */
    public String getClientIDTextField() {
        return clientIDTextField.getText();
    }

    /**
     * Sets the client's first name in the text field.
     * 
     * @param firstName The client's first name.
     */
    public void setFirstNameTextField(String firstName) {
        firstNameTextField.setText(firstName);
    }

    /**
     * Gets the client's first name from the text field.
     * 
     * @return String The client's first name.
     */
    public String getFirstNameTextField() {
        return firstNameTextField.getText();
    }

    /**
     * Sets the client's last name in the text field.
     * 
     * @param lastName The client's last name.
     */
    public void setLastNameTextField(String lastName) {
        lastNameTextField.setText(lastName);
    }

    /**
     * Gets the client's last name from the text field.
     * 
     * @return String The client's last name.
     */
    public String getLastNameTextField() {
        return lastNameTextField.getText();
    }

    /**
     * Sets the client's address in the text field.
     * 
     * @param address The client's address.
     */
    public void setAddressTextField(String address) {
        addressTextField.setText(address);
    }

    /**
     * Gets the client's address from the text field.
     * 
     * @return String The client's address.
     */
    public String getAddressTextField() {
        return addressTextField.getText();
    }

    /**
     * Sets the client's postal code in the text field.
     * 
     * @param postalCode The client's postal code.
     */
    public void setPostalCodeTextField(String postalCode) {
        postalCodeTextField.setText(postalCode);
    }

    /**
     * Gets the client's postal code from the text field.
     * 
     * @return String The client's postal code.
     */
    public String getPostalCodeTextField() {
        return postalCodeTextField.getText();
    }

    /**
     * Sets the client's phone number in the text field.
     * 
     * @param phoneNumber The client's phone number.
     */
    public void setPhoneNumberTextField(String phoneNumber) {
        phoneNumberTextField.setText(phoneNumber);
    }

    /**
     * Gets the client's phone number from the text field.
     * 
     * @return String The client's phone number.
     */
    public String getPhoneNumberTextField() {
        return phoneNumberTextField.getText();
    }

    /**
     * Sets the client type in the combo box.
     * 
     * @param clientType The client type.
     */
    public void setClientTypeComboBox(String clientType) {
        clientTypeComboBox.setSelectedItem(clientType);
    }

    /**
     * Gets the client type from the combo box.
     * 
     * @return String The client type, being either 'R' for residential, or 'C' for
     *         commerical.
     */
    public String getClientTypeComboBox() {
        return clientTypeComboBox.getSelectedItem().toString();
    }

    // ============================================================
    // Listeners
    // ============================================================

    /**
     * Adds an action listener for the save button.
     * 
     * @param listenForSaveButton An action listener for the save button.
     */
    public void addSaveListener(ActionListener listenForSaveButton) {
        saveButton.addActionListener(listenForSaveButton);
    }

    /**
     * Adds an action listener for the delete button.
     * 
     * @param listenForDeleteButton An action listener for the delete button.
     */
    public void addDeleteListener(ActionListener listenForDeleteButton) {
        deleteButton.addActionListener(listenForDeleteButton);
    }

    /**
     * Adds an action listener for the new button.
     * 
     * @param listenForNewButton An action listener for the new button.
     */
    public void addNewClientListener(ActionListener listenForNewButton) {
        newButton.addActionListener(listenForNewButton);
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Sets up the panel by creating the Panel, GridBagLayout, and
     * GridBagConstraints objects.
     */
    private void setupPanel() {
        clientPanel = new JPanel();
        gbLayout = new GridBagLayout();
        clientPanel.setLayout(gbLayout);

        // Adding insets adds a defined spacing between each UI control element.
        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(14, 30, 14, 5);
    }

    /**
     * Creates the user-interface controls for the client panel layout. Each of the
     * text fields that requires user-entered data has been set-up with input
     * validation.
     */
    private void createControls() {
        // Provides instructions for the user related to text field validation.
        errorLabel = new JLabel("<html>Note: Hover over a <font color=red>red</font> textbox for error info.<html>");
        clientVerification = new ClientTextFieldValidation();

        clientInfoLabel = new JLabel("Client Information");

        clientIDLabel = new JLabel("Client ID:");
        clientIDTextField = new JTextField("", 5);
        clientIDTextField.setEditable(false);

        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField("", 10);
        firstNameTextField.setName("firstNameTextField");
        firstNameTextField.setInputVerifier(clientVerification);

        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField("", 10);
        lastNameTextField.setName("lastNameTextField");
        lastNameTextField.setInputVerifier(clientVerification);

        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField("", 10);
        addressTextField.setName("addressTextField");
        addressTextField.setInputVerifier(clientVerification);

        postalCodeLabel = new JLabel("Postal Code:");
        postalCodeTextField = new JTextField("", 10);
        postalCodeTextField.setName("postalCodeTextField");
        postalCodeTextField.setInputVerifier(clientVerification);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberTextField = new JTextField("", 10);
        phoneNumberTextField.setName("phoneNumberTextField");
        phoneNumberTextField.setInputVerifier(clientVerification);

        // Creates a combo-box populated with either 'R' for Residential, or 'C' for
        // commercial.
        clientTypeLabel = new JLabel("Client Type:");
        String[] clientType = { "R", "C" };
        clientTypeComboBox = new JComboBox<String>(clientType);

        newButton = new JButton(" New ");
        saveButton = new JButton(" Save ");
        deleteButton = new JButton("Delete");
    }

    /**
     * Adds all of the user-interface components to the Client panel, while
     * specifying their grid position, padding, grid width, and position within the
     * grid element.
     */
    private void populatePanel() {
        addComponentToPanel(clientInfoLabel, 0, 0, 0, 0, 1, "West");
        addComponentToPanel(clientIDLabel, 0, 1, 100, 0, 1, "West");
        addComponentToPanel(clientIDTextField, 0, 1, 0, 0, 1, "East");
        addComponentToPanel(firstNameLabel, 0, 2, 0, 0, 1, "West");
        addComponentToPanel(firstNameTextField, 0, 2, 44, 0, 2, "East");
        addComponentToPanel(lastNameLabel, 0, 3, 0, 0, 1, "West");
        addComponentToPanel(lastNameTextField, 0, 3, 44, 0, 2, "East");
        addComponentToPanel(addressLabel, 0, 4, 0, 0, 1, "West");
        addComponentToPanel(addressTextField, 0, 4, 44, 0, 2, "East");
        addComponentToPanel(postalCodeLabel, 0, 5, 0, 0, 1, "West");
        addComponentToPanel(postalCodeTextField, 0, 5, 44, 0, 2, "East");
        addComponentToPanel(phoneNumberLabel, 0, 6, 0, 0, 1, "West");
        addComponentToPanel(phoneNumberTextField, 0, 6, 44, 0, 2, "East");
        addComponentToPanel(clientTypeLabel, 0, 7, 0, 0, 1, "West");
        addComponentToPanel(clientTypeComboBox, 0, 7, 0, 0, 1, "East");
        addComponentToPanel(newButton, 0, 8, 0, 0, 2, "West");
        addComponentToPanel(saveButton, 0, 8, 0, 0, 2, "Center");
        addComponentToPanel(deleteButton, 0, 8, 0, 0, 2, "East");
        addComponentToPanel(errorLabel, 0, 9, 0, 0, 2, "West");
    }

    /**
     * Adds a component to the Client panel.
     * 
     * @param component Component to be added to the client panel.
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
        clientPanel.add(component);
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