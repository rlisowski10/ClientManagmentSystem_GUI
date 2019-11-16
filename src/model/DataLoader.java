package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader implements Constants {

    public ArrayList<Client> loadClientData() {
        int idCounter = 0;
        ArrayList<Client> clientList = new ArrayList<Client>();

        try {
            Scanner sc = new Scanner(new FileReader(inputFilePath));

            while (sc.hasNext()) {
                String clientInfo[] = sc.nextLine().split(";");

                Client newClient = new Client(idCounter, clientInfo[0], clientInfo[1], clientInfo[2], clientInfo[3],
                        clientInfo[4], clientInfo[5].charAt(0));

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