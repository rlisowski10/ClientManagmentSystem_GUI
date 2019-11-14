import controller.ClientController;
import model.ClientManager;
import view.ClientView;
import view.ResultsView;
import view.SearchView;

public class ClientApp {

    // ============================================================
    // Static Methods
    // ============================================================

    public static void main(String[] args) {
        ClientView clientView = new ClientView();
        SearchView searchView = new SearchView();
        ResultsView resultsView = new ResultsView();
        ClientManager clientManager = new ClientManager();

        // Assigns the controller with the View and Model for the MVC design pattern.
        ClientController clientController = new ClientController(clientView, searchView, resultsView, clientManager);
    }
}