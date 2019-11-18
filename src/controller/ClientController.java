package controller;

import view.*;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class contains the controller functionality for the MVC design pattern.
 * The controller listens for events (such as button clicks) from the View (user
 * interface), and directs data to either the View or the back-end Model.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientController {

    // ============================================================
    // Member Variables
    // ============================================================

    private ClientView clientView;
    private SearchView searchView;
    private ResultsView resultsView;
    private ClientManager clientManager;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * The constructor for the controller, which provides the various panels to the
     * frame in the user-interface for display.
     * 
     * @param clientView    The panel that displays the client's data to the user.
     * @param searchView    The panel that allows the user to search for clients.
     * @param resultsView   The panel that displays the search results to the user.
     * @param clientManager The publicly-exposed back-end object that handles data
     *                      and requests from the front-end.
     */
    public ClientController(ClientView clientView, SearchView searchView, ResultsView resultsView,
            ClientManager clientManager) {
        this.clientView = clientView;
        this.searchView = searchView;
        this.resultsView = resultsView;
        this.clientManager = clientManager;

        // Sets the various panels to display in the front-end frame.
        ClientFrame clientFrame = new ClientFrame(clientView, searchView, resultsView);

        updateResultsTable(clientManager.getClientList());
        setupListeners();
    }

    // ============================================================
    // Listeners
    // ============================================================

    /**
     * A listener that handles a change in the results list selection by the user,
     * in the Results panel. If a new row is selected, that client's data is
     * displayed in the Client panel.
     */
    private class addTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            // Checks to make sure that changes are not still continuing to be made in the
            // list area.
            if (!lse.getValueIsAdjusting()) {
                // Gets the client ID from the list selection and displays the client data in
                // the client panel.
                String clientID = resultsView.getSelectedTableRowClientID();
                populateClientView(clientID);
            }
        }
    }

    /**
     * A listener for a save-button event from the Client panel. The entered client
     * information is saved to the database.
     */
    private class addSaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clientID = clientView.getClientIDTextField();
            String firstName = clientView.getFirstNameTextField();
            String lastName = clientView.getLastNameTextField();
            String address = clientView.getAddressTextField();
            String postalCode = clientView.getPostalCodeTextField();
            String phoneNumber = clientView.getPhoneNumberTextField();
            String clientType = clientView.getClientTypeComboBox();

            // Saves the entered client information to the database.
            clientManager.saveClient(clientID, firstName, lastName, address, postalCode, phoneNumber, clientType);

            // Updates the results table with the new client information.
            updateResultsTable(clientManager.getClientList());
        }
    }

    /**
     * A listener for a delete-button event from the Client panel. The selected
     * client is removed from the client database, and the results table is updated
     * from the database.
     */
    private class addDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientManager.deleteClient();
            updateResultsTable(clientManager.getClientList());
        }
    }

    /**
     * A listener for a new-button event from the Client panel. The text fields in
     * the client panel are cleared of any text, and a new ID is generated for the
     * new client.
     */
    private class addNewClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearTextFields();

            // Creates an ID number for the new client, based on the previous maximum ID
            // number found in the database.
            int newClientID = clientManager.getMaxID() + 1;
            String newClientIDString = Integer.toString(newClientID);
            clientView.setClientIDTextField(newClientIDString);
        }
    }

    /**
     * A listener for a search-button event from the Search panel. Updates the
     * results table based on which search radio button was active, and the search
     * text provided.
     */
    private class addSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = searchView.getSearchParameterTextField();

            // Retreives a filtered client list based the search parameter and which radio
            // button was active.
            if (searchView.clientIDRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByID(searchText));
            } else if (searchView.lastNameRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByLastName(searchText));
            } else if (searchView.clientTypeRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByClientType(searchText));
            }
        }
    }

    /**
     * A listener for a clear-button event from the Search panel. Updates the
     * results table with all of the clients in the database.
     */
    private class addClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {            
            // Resets the search text field, and updates the results table with all clients.
            searchView.setSearchParameterTextField("");
            updateResultsTable(clientManager.getClientList());
        }
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Updates the results table with an arraylist of client results.
     * 
     * @param clientList A list of clients in string arraylist format, based on the
     *                   search query.
     */
    private void updateResultsTable(ArrayList<String[]> clientList) {
        resultsView.updateResultsTable(clientList);
    }

    /**
     * Connects the listener methods in the front-end controls with the controller
     * listeners.
     */
    private void setupListeners() {
        resultsView.addTableSelectionListener(new addTableSelectionListener());
        clientView.addSaveListener(new addSaveListener());
        clientView.addDeleteListener(new addDeleteListener());
        clientView.addNewClientListener(new addNewClientListener());
        searchView.addSearchListener(new addSearchListener());
        searchView.addClearListener(new addClearListener());
    }

    /**
     * Populates the client panel with data for the selected client, based on the
     * Client ID provided from the front-end.
     * 
     * @param clientID The ID number for the client from the front-end.
     */
    private void populateClientView(String clientID) {
        try {
            clientManager.setCurrentClient(clientID);
            String firstName = clientManager.getCurrentClientFirstName();
            String lastName = clientManager.getCurrentClientLastName();
            String address = clientManager.getCurrentClientAddress();
            String postalCode = clientManager.getCurrentClientPostalCode();
            String phoneNumber = clientManager.getCurrentClientPhoneNumber();
            String clientType = Character.toString(clientManager.getCurrentClientType());

            clientView.setClientIDTextField(clientID);
            clientView.setFirstNameTextField(firstName);
            clientView.setLastNameTextField(lastName);
            clientView.setAddressTextField(address);
            clientView.setPostalCodeTextField(postalCode);
            clientView.setPhoneNumberTextField(phoneNumber);
            clientView.setClientTypeComboBox(clientType);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error: A current client has not been set.");
        }
    }

    /**
     * Clears all text fields in the Client panel.
     */
    private void clearTextFields() {
        clientView.setClientIDTextField("");
        clientView.setFirstNameTextField("");
        clientView.setLastNameTextField("");
        clientView.setAddressTextField("");
        clientView.setPostalCodeTextField("");
        clientView.setPhoneNumberTextField("");
        clientView.setClientTypeComboBox("R");
    }
}