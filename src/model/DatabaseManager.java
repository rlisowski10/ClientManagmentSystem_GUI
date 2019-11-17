package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Add functionality to check for an existing database, creating a new one if it doesn't exist
class DatabaseManager implements Constants {

    // ============================================================
    // Member Variables
    // ============================================================

    Connection jdbc_connection;

    // ============================================================
    // Constructors
    // ============================================================

    public DatabaseManager() {
        jdbc_connection = connectToDB();
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

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

    public void removeClientTable() {
        String sql = "DROP TABLE " + databaseTableName;
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public ArrayList<Client> getClientByIDFromDB(String clientID) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE ID = ?", clientID);
        return clientList;
    }

    public ArrayList<Client> getClientsByLastNameFromDB(String lastName) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE LASTNAME LIKE ? '%'", lastName);
        return clientList;
    }

    public ArrayList<Client> getClientsByClientTypeFromDB(String clientType) {
        ArrayList<Client> clientList = queryDBForClients(" WHERE CLIENTTYPE = ?", clientType);
        return clientList;
    }

    public ArrayList<Client> getAllClientsFromDB() {
        ArrayList<Client> clientList = queryDBForClients("", null);
        return clientList;
    }

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

    private Connection connectToDB() {
        Connection jdbc_connection = null;

        try {
            jdbc_connection = DriverManager.getConnection(databaseConnection, databaseLogin, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jdbc_connection;
    }

    private ArrayList<Client> queryDBForClients(String clientQuery, String clientID) {
        String sql = "SELECT * FROM " + databaseTableName + clientQuery;
        ArrayList<Client> clientList = new ArrayList<Client>();

        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            if (clientQuery != "") {
                pStat.setString(1, clientID);
            }
            ResultSet result = pStat.executeQuery();

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