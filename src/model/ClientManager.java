package model;

import java.util.ArrayList;

public class ClientManager {

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
        currentClient = databaseManager.getClientFromDB(clientID);
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

    public void saveClientToDB(String firstName, String lastName, String address, String postalCode, String phoneNumber, String clientType) {
        currentClient.setFirstName(firstName);
        currentClient.setLastName(lastName);
        currentClient.setAddress(address);
        currentClient.setPostalCode(postalCode);
        currentClient.setPhoneNumber(phoneNumber);
        currentClient.setClientType(clientType.charAt(0));
        
        databaseManager.updateItemInDB(currentClient);
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void loadDataFromInputFile() {
        DataLoader dataLoader = new DataLoader();
        ArrayList<Client> clientList = dataLoader.loadClientData();

        // TODO: Have this check to see if both the database and table exists.
        boolean tempTest = false;
        if (tempTest) {
            databaseManager.createClientTable();

            for (Client client : clientList) {
                databaseManager.addItemToDB(client);
            }
        }
    }
}