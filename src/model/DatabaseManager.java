package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles all database queries and connections.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
class DatabaseManager implements Constants {

    // ============================================================
    // Member Variables
    // ============================================================

    Connection jdbc_connection;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * Constructor that calls a method that sets the database connection paramter.
     */
    public DatabaseManager() {
        connectToDB();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    /**
     * Creates a new client table using a Prepared Statement based on the
     * requirements of the lab handout.
     */
    public void createClientTable() {
        String sql = "CREATE TABLE " + databaseTableName + "(" + "ID INT(4) NOT NULL, "
                + "FIRSTNAME VARCHAR(20) NOT NULL, " + "LASTNAME VARCHAR(20) NOT NULL, "
                + "ADDRESS VARCHAR(50) NOT NULL, " + "POSTALCODE VARCHAR(7) NOT NULL, "
                + "PHONENUMBER VARCHAR(12) NOT NULL, " + "CLIENTTYPE CHAR NOT NULL, " + "PRIMARY KEY ( id ))";
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the client table using a Prepared Statement.
     */
    public void removeClientTable() {
        String sql = "DROP TABLE " + databaseTableName;
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new client to the database client table using a Prepared Statement.
     * Note that the client ID provided will be one greater than the largest client
     * ID that exists in the database.
     * 
     * @param client The client to insert into the database table.
     */
    public void addItemToDB(Client client) {
        String sql = "INSERT INTO " + databaseTableName + " VALUES ( ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.setInt(1, client.getClientID());
            pStat.setString(2, client.getFirstName());
            pStat.setString(3, client.getLastName());
            pStat.setString(4, client.getAddress());
            pStat.setString(5, client.getPostalCode());
            pStat.setString(6, client.getPhoneNumber());
            pStat.setString(7, String.valueOf(client.getClientType()));
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing client in the database's client table using a Prepared
     * Statement.
     * 
     * @param client The client to be updated in the database table.
     */
    public void updateItemInDB(Client client) {
        String sql = "UPDATE " + databaseTableName + " SET FIRSTNAME = ?, " + "LASTNAME = ?, " + "ADDRESS = ?, "
                + "POSTALCODE = ?, " + "PHONENUMBER = ?, " + "CLIENTTYPE = ? " + "WHERE ID = ?;";
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.setString(1, client.getFirstName());
            pStat.setString(2, client.getLastName());
            pStat.setString(3, client.getAddress());
            pStat.setString(4, client.getPostalCode());
            pStat.setString(5, client.getPhoneNumber());
            pStat.setString(6, Character.toString(client.getClientType()));
            pStat.setInt(7, client.getClientID());
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of clients with the provided Client ID from the database. Note
     * that there should only be one record returned, but an arraylist has been
     * provided just in case there are more than one for some reason.
     * 
     * @param clientID The client ID to be used for the database record search.
     * @return ArrayList<Client> An arraylist containing clients that match the ID
     *         query.
     */
    public ArrayList<Client> getClientByIDFromDB(String clientID) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE ID = ?", clientID);
        return clientList;
    }

    /**
     * Gets a list of clients with the provided last name from the database. Note
     * that partial matches to the start of the last name will also be included in
     * the client list.
     * 
     * @param lastName The last name of the client to search the database for.
     * @return ArrayList<Client> An arraylist containing clients that match the last
     *         name query.
     */
    public ArrayList<Client> getClientsByLastNameFromDB(String lastName) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE LASTNAME LIKE ? '%'", lastName);
        return clientList;
    }

    /**
     * Gets a list of clients with the provided client type from the database.
     * 
     * @param clientType The type of client.
     * @return ArrayList<Client> An arraylist containing clients that match the
     *         client type query.
     */
    public ArrayList<Client> getClientsByClientTypeFromDB(String clientType) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE CLIENTTYPE = ?", clientType);
        return clientList;
    }

    /**
     * Gets all clients from the database.
     * 
     * @return ArrayList<Client> An arraylist containing all clients.
     */
    public ArrayList<Client> getAllClientsFromDB() {
        ArrayList<Client> clientList = queryDBForClients("", null);
        return clientList;
    }

    /**
     * Deletes a client from the database using the provdied Client object's ID.
     * 
     * @param client The client object to delete from the database.
     */
    public void deleteClientFromDB(Client client) {
        String sql = "DELETE FROM " + databaseTableName + " WHERE ID = ?;";
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.setInt(1, client.getClientID());
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the maximum client ID value from the database. This is used for setting
     * a new client's ID value.
     * 
     * @return int The maximum client ID value from the database.
     */
    public int getMaxIDFromDB() {
        int maxID = 0;
        String sql = "SELECT MAX(ID) AS 'Max' FROM " + databaseTableName;

        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            ResultSet result = pStat.executeQuery();

            while (result.next()) {
                maxID = result.getInt("Max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxID;
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Provides a connetion to the Database using the database connection line and
     * credentials stored in the Constants.java file.
     */
    private void connectToDB() {
        try {
            jdbc_connection = DriverManager.getConnection(databaseConnection, databaseLogin, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A general method that queries the database for records based on the provided
     * query. If no additional query is specified, all client records are returned
     * from the client table.
     * 
     * @param clientQuery        A SQL query string to specify the query filter
     *                           (e.g. WHERE ID = ?).
     * @param selectionParameter The selection parameter to query the database with
     *                           (e.g. '5').
     * @return ArrayList<Client>
     */
    private ArrayList<Client> queryDBForClients(String clientQuery, String selectionParameter) {

        // Sets the database selection query, which would return all records if the
        // clientQuery is empty (i.e. "").
        String sql = "SELECT * FROM " + databaseTableName + clientQuery;
        ArrayList<Client> clientList = new ArrayList<Client>();

        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);

            // Uses a Prepared Statement to enter the selection parameter, if a filter is
            // provided.
            if (clientQuery != "") {
                pStat.setString(1, selectionParameter);
            }

            // Executes the prepared statement query.
            ResultSet result = pStat.executeQuery();

            // Adds the data from each result to a Client object, which is then added to a
            // Client list for return.
            while (result.next()) {
                Client newClient = new Client(result.getInt("ID"), result.getString("FIRSTNAME"),
                        result.getString("LASTNAME"), result.getString("ADDRESS"), result.getString("POSTALCODE"),
                        result.getString("PHONENUMBER"), result.getString("CLIENTTYPE").charAt(0));
                clientList.add(newClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }
}