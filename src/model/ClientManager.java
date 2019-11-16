package model;

import java.util.ArrayList;

public class ClientManager {
    
    DatabaseManager databaseManager;

    public ClientManager() {
        databaseManager = new DatabaseManager();

        DataLoader dataLoader = new DataLoader();
        ArrayList<Client> clientList = dataLoader.loadClientData();

        // TODO: Have this check to see if both the database and table exists.
        if (true) {
            databaseManager.createClientTable();
        }

        for (Client client : clientList) {
            databaseManager.addItemToDB(client);
        }

        databaseManager.removeClientTable();
    }
}