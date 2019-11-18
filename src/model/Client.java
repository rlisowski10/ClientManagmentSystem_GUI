package model;

/**
 * This class contains the necessary datafields to represent a client.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
class Client {

    // ============================================================
    // Member Variables
    // ============================================================

    private int clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private char clientType;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * @param clientID    The ID of the client.
     * @param firstName   The first name of the client.
     * @param lastNamethe The last name of the client.
     * @param address     The address of the client.
     * @param postalCode  The postal code of the client.
     * @param phoneNumber The phone number for the client.
     * @param clientType  The type of the client.
     */
    public Client(int clientID, String firstName, String lastName, String address, String postalCode,
            String phoneNumber, char clientType) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.clientType = clientType;
    }

    // ============================================================
    // Accessors
    // ============================================================

    /**
     * Gets the ID for the client.
     * 
     * @return int The ID for the client.
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Gets the type of the client.
     * 
     * @return char The type of the client, being either 'R' for residential, or 'C'
     *         for commercial.
     */
    public char getClientType() {
        return clientType;
    }

    /**
     * Sets the type of the client.
     * 
     * @param clientType The type of the client, being either 'R' for residential,
     *                   or 'C' for commercial.
     */
    public void setClientType(char clientType) {
        this.clientType = clientType;
    }

    /**
     * Gets the phone number for the client.
     * 
     * @return String The phone number for the client.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number for the client.
     * 
     * @param phoneNumber The phone number for the client.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the postal code for the client.
     * 
     * @return String The postal code for the client.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the client.
     * 
     * @param postalCode The postal code of the client.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the address of the client.
     * 
     * @return String The address of the client.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     * 
     * @param address The address of the client.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the last name of the client.
     * 
     * @return String The last name of the client.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the client.
     * 
     * @param lastName The last name of the client.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of the client.
     * 
     * @return String The first name of the client.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the client.
     * 
     * @param firstName The first name of the client.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the ID of the client.
     * 
     * @param clientID The ID of the client.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}