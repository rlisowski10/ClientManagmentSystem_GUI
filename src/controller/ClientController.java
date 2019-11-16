package controller;

import view.*;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

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

    public ClientController(ClientView clientView, SearchView searchView, ResultsView resultsView,
            ClientManager clientManager) {
        this.clientView = clientView;
        this.searchView = searchView;
        this.resultsView = resultsView;
        this.clientManager = clientManager;
        ClientFrame clientFrame = new ClientFrame(clientView, searchView, resultsView);

        updateResultsTable();
        resultsView.addTableSelectionListener(new addTableSelectionListener());
        clientView.addSaveListener(new addSaveListener());
    }

    // ============================================================
    // Action Listeners
    // ============================================================

    private class addTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (!lse.getValueIsAdjusting()) {
                String clientID = resultsView.getSelectedTableRowClientID();
                populateClientView(clientID);
            }
        }
    }

    private class addSaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = clientView.getFirstNameTextField();
            String lastName = clientView.getLastNameTextField();
            String address = clientView.getAddressTextField();
            String postalCode = clientView.getPostalCodeTextField();
            String phoneNumber = clientView.getPhoneNumberTextField();
            String clientType = clientView.getClientTypeComboBox();

            clientManager.saveClientToDB(firstName, lastName, address, postalCode, phoneNumber, clientType);
            updateResultsTable();
        }
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void updateResultsTable() {
        resultsView.updateResultsTable(clientManager.getClientList());
    }
    
    private void populateClientView(String clientID) {
        clientManager.setCurrentClient(clientID);
        String firstName = clientManager.getCurrentClientFirstName();
        String lastName = clientManager.getCurrentClientLastName();
        String address = clientManager.getCurrentClientAddress();
        String postalCode = clientManager.getCurrentClientPostalCode();
        String phoneNumber = clientManager.getCurrentClientPhoneNumber();
        String clientType = Character.toString(clientManager.getCurrentClientType());

        try {
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
}