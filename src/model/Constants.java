package model;

/**
 * An interface used to group related, similar constants that are used across
 * all the relevant classes.
 * 
 * @author Ryan Lisowski
 * @version 1.0
 * @since November 6, 2019
 */
public interface Constants {

    // ============================================================
    // Member Variables
    // ============================================================

    static final boolean createNewDatabaseTable = false;
    static final String databaseConnection = "jdbc:mysql://localhost:3306/clientdb?verifyServerCertificate=false&useSSL=true";

    static final String databaseName = "clientdb";
    static final String databaseTableName = "clients";
    static final String databaseLogin = "rlisowski";
    static final String databasePassword = "jB!#63Mx7NiLWg";

    static final String inputFilePath = "./src/resources/clients.txt";
}