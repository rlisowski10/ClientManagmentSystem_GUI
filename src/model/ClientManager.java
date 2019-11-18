package model;

import java.util.ArrayList;

public class ClientManager implements Constants{

    // ============================================================
    // Member Variables
    // ============================================================

    DatabaseManager databaseManager;
    Client currentClient;

    // ============================================================
    // Constructors
    // ============================================================

    public ClientManager() {
        databaseManager = new DatabaseManager();
        loadDataFromInputFile();
    }

    // ============================================================
    // Accessors
    // ============================================================

    public void setCurrentClient(String clientID) {
        currentClient = databaseManager.getClientByIDFromDB(clientID).get(0);
    }

    public String getCurrentClientFirstName() {
        return currentClient.getFirstName();
    }

    public String getCurrentClientLastName() {
        return currentClient.getLastName();
    }

    public String getCurrentClientAddress() {
        return currentClient.getAddress();
    }

    public String getCurrentClientPostalCode() {
        return currentClient.getPostalCode();
    }

    public String getCurrentClientPhoneNumber() {
        return currentClient.getPhoneNumber();
    }

    public char getCurrentClientType() {
        return currentClient.getClientType();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public ArrayList<String[]> getClientList() {
        ArrayList<Client> clientList = databaseManager.getAllClientsFromDB();
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    public ArrayList<String[]> getClientListByID(String clientID) {
        ArrayList<Client> clientList = databaseManager.getClientByIDFromDB(clientID);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    public ArrayList<String[]> getClientListByLastName(String lastName) {
        ArrayList<Client> clientList = databaseManager.getClientsByLastNameFromDB(lastName);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    public ArrayList<String[]> getClientListByClientType(String clientType) {
        ArrayList<Client> clientList = databaseManager.getClientsByClientTypeFromDB(clientType);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    public void saveClient(String clientID, String firstName, String lastName, String address, String postalCode,
            String phoneNumber, String clientType) {
        setCurrentClientData(clientID, firstName, lastName, address, postalCode, phoneNumber, clientType);

        if (Integer.parseInt(clientID) <= databaseManager.getMaxIDFromDB()) {
            databaseManager.updateItemInDB(currentClient);
        } else {
            databaseManager.addItemToDB(currentClient);
        }
    }

    private void setCurrentClientData(String clientID, String firstName, String lastName, String address,
            String postalCode, String phoneNumber, String clientType) {
        currentClient.setClientID(Integer.parseInt(clientID));
        currentClient.setFirstName(firstName);
        currentClient.setLastName(lastName);
        currentClient.setAddress(address);
        currentClient.setPostalCode(postalCode);
        currentClient.setPhoneNumber(phoneNumber);
        currentClient.setClientType(clientType.charAt(0));
    }

    public void deleteClient() {
        databaseManager.deleteClientFromDB(currentClient);
    }

    public int getMaxID() {
        return databaseManager.getMaxIDFromDB();
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void loadDataFromInputFile() {
        DataLoader dataLoader = new DataLoader();
        ArrayList<Client> clientList = dataLoader.loadClientData();

        if (createNewTable) {
            databaseManager.createClientTable();

            for (Client client : clientList) {
                databaseManager.addItemToDB(client);
            }
        }
    }

    private ArrayList<String[]> clientListForDisplay(ArrayList<Client> clientList) {
        ArrayList<String[]> clientListAsStrings = new ArrayList<String[]>();

        for (Client client : clientList) {
            String clientID = Integer.toString(client.getClientID());
            String clientName = client.getFirstName() + " " + client.getLastName();
            String clientType = String.valueOf(client.getClientType());

            String[] clientReduced = new String[] { clientID, clientName, clientType };
            clientListAsStrings.add(clientReduced);
        }
        return clientListAsStrings;
    }
}