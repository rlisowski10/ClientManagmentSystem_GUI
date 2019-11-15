package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Add functionality to check for an existing database, creating a new one if it doesn't exist
public class DatabaseManager implements Constants {
    
    public static void addItemToDB(Client client) {
        Connection jdbc_connection = connectToDB();
        createClientTable(jdbc_connection);

        System.out.println("Test");
    }

    private static void createClientTable(Connection jdbc_connection) {
        String sql = "CREATE TABLE " + databaseTableName + "(" + "ID INT(4) NOT NULL, "
                + "FIRSTNAME VARCHAR2(20) NOT NULL, " + "LASTNAME VARCHAR2(20) NOT NULL, "
                + "ADDRESS VARCHAR2(50) NOT NULL, " + "POSTALCODE CHAR(7) NOT NULL, "
                + "PHONENUMBER CHAR(12) NOT NULL, " + "CLIENTTYPE CHAR(1) NOT NULL, " + "PRIMARY KEY (ID))";
        try {
            PreparedStatement pStat = jdbc_connection.prepareStatement(sql);
            pStat.executeUpdate();
            System.out.println("Created Table " + databaseTableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection connectToDB() {
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