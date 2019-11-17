package controller;

import view.*;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        updateResultsTable(clientManager.getClientList());
        resultsView.addTableSelectionListener(new addTableSelectionListener());
        clientView.addSaveListener(new addSaveListener());
        clientView.addDeleteListener(new addDeleteListener());
        clientView.addNewClientListener(new addNewClientListener());
        searchView.addSearchListener(new addSearchListener());
        searchView.addClearListener(new addClearListener());
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

    // TODO: Fix the error where after editing/deleting row, clientView not clearing
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

            clientManager.saveClient(clientID, firstName, lastName, address, postalCode, phoneNumber, clientType);
            updateResultsTable(clientManager.getClientList());
        }
    }

    private class addDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientManager.deleteClient();
            updateResultsTable(clientManager.getClientList());
        }
    }

    private class addNewClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearTextFields();

            int newClientID = clientManager.getMaxID() + 1;
            String newClientIDString = Integer.toString(newClientID);
            clientView.setClientIDTextField(newClientIDString);
        }
    }

    private class addSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = searchView.getSearchParameterTextField();

            if (searchView.clientIDRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByID(searchText));
            } else if (searchView.lastNameRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByLastName(searchText));
            } else if (searchView.clientTypeRadioButtonStatus()) {
                updateResultsTable(clientManager.getClientListByClientType(searchText));
            }
        }
    }

    private class addClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchView.setSearchParameterTextField("");
            updateResultsTable(clientManager.getClientList());
        }
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void updateResultsTable(ArrayList<String[]> clientList) {
        resultsView.updateResultsTable(clientList);
    }

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