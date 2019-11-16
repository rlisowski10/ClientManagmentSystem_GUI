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

    public ArrayList<Client> getClientsFromDB() {
        ArrayList<Client> clientList = new ArrayList<Client>();
        String sql = "SELECT * FROM " + databaseTableName;

        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            ResultSet result = pStat.executeQuery();

            if (result.next()) {
                Client newClient = new Client(result.getInt("ID"), result.getString("FIRSTNAME"), result.getString("LASTNAME"), result.getString("ADDRESS"), result.getString("POSTALCODE"), result.getString("PHONENUMBER"), result.getString("CLIENTTYPE").charAt(0));
                clientList.add(newClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
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
}