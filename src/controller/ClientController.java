package controller;

import view.*;
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

    public ClientController(ClientView clientView, SearchView searchView, ResultsView resultsView, ClientManager clientManager) {
        this.clientView = clientView;
        this.searchView = searchView;
        this.clientView = clientView;
        this.clientManager = clientManager;

        ClientFrame clientFrame = new ClientFrame(clientView, searchView, resultsView);
    }
}