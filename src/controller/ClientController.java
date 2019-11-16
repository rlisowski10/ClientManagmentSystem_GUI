package controller;

import view.*;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.*;

public class ClientController {

    // ============================================================
    // Member Variables
    // ============================================================

    private ClientView clientView;
    private SearchView searchView;
    private ResultsView resultsView;
    private ClientManager clientManager;

    // ============================================================
    // Constructors
    // ============================================================

    public ClientController(ClientView clientView, SearchView searchView, ResultsView resultsView,
            ClientManager clientManager) {
        this.clientView = clientView;
        this.searchView = searchView;
        this.clientView = clientView;
        this.clientManager = clientManager;
        ClientFrame clientFrame = new ClientFrame(clientView, searchView, resultsView);

        resultsView.updateResultsTable(clientManager.getClientList());
        resultsView.addTableSelectionListener(new addTableSelectionListener());
    }

    // ============================================================
    // Private Action Listeners
    // ============================================================

    private class addTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (!lse.getValueIsAdjusting()) {
                System.out.println("Selection Changed");
            }
        }
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

}