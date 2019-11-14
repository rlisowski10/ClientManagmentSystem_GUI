package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionListener;

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

    // ============================================================
    // Constructors
    // ============================================================

    public ClientView() {
        setupPanel();
        createControls();
        populatePanel();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public JPanel getPanel() {
        return clientPanel;
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void setupPanel() {
        clientPanel = new JPanel();
        gbLayout = new GridBagLayout();
        clientPanel.setLayout(gbLayout);

        //Border panelBorder = BorderFactory.createLineBorder(Color.black);
        //clientPanel.setBorder(panelBorder);

        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(10, 5, 10, 5);
    }

    private void createControls() {
        clientInfoLabel = new JLabel("Client Information");
        clientIDLabel = new JLabel("Client ID:");
        clientIDTextField = new JTextField("", 5);
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField("", 10);
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField("", 10);
        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField("", 10);
        postalCodeLabel = new JLabel("Postal Code:");
        postalCodeTextField = new JTextField("", 10);
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberTextField = new JTextField("", 10);

        clientTypeLabel = new JLabel("Client Type:");
        String[] clientType = { "R", "C" };
        clientTypeComboBox = new JComboBox<String>(clientType);

        newButton = new JButton(" New ");
        saveButton = new JButton(" Save ");
        deleteButton = new JButton("Delete");
    }

    private void populatePanel() {
        addComponentToPanel(clientInfoLabel, 0, 0, 0, 20, 1, "West");
        addComponentToPanel(clientIDLabel, 0, 1, 100, 0, 1, "West");
        addComponentToPanel(clientIDTextField, 1, 1, 0, 0, 1, "West");
        addComponentToPanel(firstNameLabel, 0, 2, 0, 0, 1, "West");
        addComponentToPanel(firstNameTextField, 1, 2, 0, 0, 1, "West");
        addComponentToPanel(lastNameLabel, 0, 3, 0, 0, 1, "West");
        addComponentToPanel(lastNameTextField, 1, 3, 0, 0, 1, "West");
        addComponentToPanel(addressLabel, 0, 4, 0, 0, 1, "West");
        addComponentToPanel(addressTextField, 1, 4, 0, 0, 1, "West");
        addComponentToPanel(postalCodeLabel, 0, 5, 0, 0, 1, "West");
        addComponentToPanel(postalCodeTextField, 1, 5, 0, 0, 1, "West");
        addComponentToPanel(phoneNumberLabel, 0, 6, 0, 0, 1, "West");
        addComponentToPanel(phoneNumberTextField, 1, 6, 0, 0, 1, "West");
        addComponentToPanel(clientTypeLabel, 0, 7, 0, 0, 1, "West");
        addComponentToPanel(clientTypeComboBox, 1, 7, 0, 0, 1, "West");
        addComponentToPanel(newButton, 0, 8, 0, 0, 2, "West");
        addComponentToPanel(saveButton, 0, 8, 0, 0, 2, "Center");
        addComponentToPanel(deleteButton, 0, 8, 0, 0, 2, "East");
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
        clientPanel.add(component);
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