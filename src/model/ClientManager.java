package model;

import java.util.ArrayList;

/**
 * This class is the only publicly-available class in the back-end, and handles
 * all functionality related to the Clients.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientManager implements Constants {

    // ============================================================
    // Member Variables
    // ============================================================

    DatabaseManager databaseManager;
    Client currentClient;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * The constructor for the Client Manager, which creates a new Database Manager
     * object and calls a method to load the initial input data from the text file
     * provided.
     */
    public ClientManager() {
        databaseManager = new DatabaseManager();
        loadDataFromInputFile();
    }

    // ============================================================
    // Accessors
    // ============================================================

    /**
     * Sets the current client object from the provided Client ID.
     * 
     * @param clientID The Client ID for the current Client.
     */
    public void setCurrentClient(String clientID) {
        currentClient = databaseManager.getClientByIDFromDB(clientID).get(0);
    }

    /**
     * Gets the first name of the current client.
     * 
     * @return String The current client's first name.
     */
    public String getCurrentClientFirstName() {
        return currentClient.getFirstName();
    }

    /**
     * Gets the last name of the current client.
     * 
     * @return String The current client's last name.
     */
    public String getCurrentClientLastName() {
        return currentClient.getLastName();
    }

    /**
     * Gets the address of the current client.
     * 
     * @return String The current client's address.
     */
    public String getCurrentClientAddress() {
        return currentClient.getAddress();
    }

    /**
     * Gets the postal code of the current client.
     * 
     * @return String The current client's postal code.
     */
    public String getCurrentClientPostalCode() {
        return currentClient.getPostalCode();
    }

    /**
     * Gets the phone number of the current client.
     * 
     * @return String The current client's phone number.
     */
    public String getCurrentClientPhoneNumber() {
        return currentClient.getPhoneNumber();
    }

    /**
     * Gets the client type of the current client.
     * 
     * @return char The current client's type.
     */
    public char getCurrentClientType() {
        return currentClient.getClientType();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    /**
     * Gets the full list of clients from the database.
     * 
     * @return ArrayList<String[]> The full list of clients in String arraylist
     *         format.
     */
    public ArrayList<String[]> getClientList() {
        ArrayList<Client> clientList = databaseManager.getAllClientsFromDB();
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    /**
     * Gets a list of clients based on the provided ID. There should only be one
     * Client returned.
     * 
     * @param clientID The ID of the Client to retreive.
     * @return ArrayList<String[]> The list of clients in String arraylist format.
     */
    public ArrayList<String[]> getClientListByID(String clientID) {
        ArrayList<Client> clientList = databaseManager.getClientByIDFromDB(clientID);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    /**
     * Gets a list of client based on the provided last name.
     * 
     * @param lastName The last name of the client to search for. Note that this can
     *                 be a portion of the last name, as the database will return
     *                 all results that contain the lastName text at the start of
     *                 their last name.
     * @return ArrayList<String[]> The list of clients in String arraylist format.
     */
    public ArrayList<String[]> getClientListByLastName(String lastName) {
        ArrayList<Client> clientList = databaseManager.getClientsByLastNameFromDB(lastName);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    /**
     * Gets a list of clients based on the provided Client Type.
     * 
     * @param clientType The client type to search for.
     * @return ArrayList<String[]> The list of clients in String arraylist format.
     */
    public ArrayList<String[]> getClientListByClientType(String clientType) {
        ArrayList<Client> clientList = databaseManager.getClientsByClientTypeFromDB(clientType);
        ArrayList<String[]> clientListAsStrings = clientListForDisplay(clientList);

        return clientListAsStrings;
    }

    /**
     * Sets the client data provided into the current client object, and passes this
     * object to the Database Manager for addition/updates in the database.
     * 
     * @param clientID    The ID of the client.
     * @param firstName   The first name of the client.
     * @param lastNamethe The last name of the client.
     * @param address     The address of the client.
     * @param postalCode  The postal code of the client.
     * @param phoneNumber The phone number for the client.
     * @param clientType  The type of the client.
     */
    public void saveClient(String clientID, String firstName, String lastName, String address, String postalCode,
            String phoneNumber, String clientType) {
        // Updates the current client object with the information provided by the user.
        setCurrentClientData(clientID, firstName, lastName, address, postalCode, phoneNumber, clientType);

        // Determines based on the provided client ID as to whether the client
        // information is for a new or existing client, and calls the appropriate
        // method.
        if (Integer.parseInt(clientID) <= databaseManager.getMaxIDFromDB()) {
            databaseManager.updateItemInDB(currentClient);
        } else {
            databaseManager.addItemToDB(currentClient);
        }
    }

    /**
     * Provides the Database Manager with a client object to delete from the
     * database.
     */
    public void deleteClient() {
        databaseManager.deleteClientFromDB(currentClient);
    }

    /**
     * Gets the maximum ID number in the database.
     * 
     * @return int The maximum ID number in the database.
     */
    public int getMaxID() {
        return databaseManager.getMaxIDFromDB();
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Sets the current client object instance variables to the information provided
     * from the front-end.
     * 
     * @param clientID    The ID of the client.
     * @param firstName   The first name of the client.
     * @param lastNamethe The last name of the client.
     * @param address     The address of the client.
     * @param postalCode  The postal code of the client.
     * @param phoneNumber The phone number for the client.
     * @param clientType  The type of the client.
     */
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

    /**
     * Reads the client data from the input text file into a client arraylist. If
     * the switch provided in the Constants.java file is set to true, a new database
     * table is created that will be updated with the information from the text
     * file.
     */
    private void loadDataFromInputFile() {
        DataLoader dataLoader = new DataLoader();
        ArrayList<Client> clientList = dataLoader.loadClientData();

        // Only creates a new table and enters the input data from the text file into
        // the database if the createNewDatabaseTable boolean value is set to True in
        // the Constants file.
        if (createNewDatabaseTable) {
            // Creates a new clients table in the database.
            databaseManager.createClientTable();

            // Adds each client item from the text file to the database.
            for (Client client : clientList) {
                databaseManager.addItemToDB(client);
            }
        }
    }

    /**
     * Creates a formatted String Arraylist from an arraylist of Clients. This
     * resultant list is formatted in the correct manner for display in the
     * front-end table.
     * 
     * @param clientList An arraylist of Clients which has information to be
     *                   displayed in the front-end.
     * @return ArrayList<String[]> An arraylist of client information in string
     *         format for display in the front-end.
     */
    private ArrayList<String[]> clientListForDisplay(ArrayList<Client> clientList) {
        ArrayList<String[]> clientListAsStrings = new ArrayList<String[]>();

        // Extracts the required information from the list of client objects, adding
        // that information to a String arraylist.
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