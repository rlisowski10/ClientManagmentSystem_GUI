package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads the input client text file for client data, and returns it
 * for potential transfer to the database.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
class DataLoader implements Constants {

    /**
     * Loads the client data from the input text file into an arraylist of clients.
     * 
     * @return ArrayList<Client> A list containing all clients from the input text
     *         file.
     */
    public ArrayList<Client> loadClientData() {
        int idCounter = 0;
        ArrayList<Client> clientList = new ArrayList<Client>();

        try {
            Scanner sc = new Scanner(new FileReader(inputFilePath));

            // Splits each line in the text file by the semicolon delimiter.
            while (sc.hasNext()) {
                String clientInfo[] = sc.nextLine().split(";");

                // Adds all information from the line into a Client object.
                Client newClient = new Client(idCounter, clientInfo[0], clientInfo[1], clientInfo[2], clientInfo[3],
                        clientInfo[4], clientInfo[5].charAt(0));

                // Adds the Client object to a list of clients and increments the ID counter.
                clientList.add(newClient);
                idCounter++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File " + inputFilePath + " not Found!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientList;
    }
}