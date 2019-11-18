import controller.ClientController;
import model.ClientManager;
import view.ClientView;
import view.ResultsView;
import view.SearchView;

/**
 * The class that holds the main method for the application, which serves as an
 * entry-point.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-17
 */
public class ClientApp {

    // ============================================================
    // Static Methods
    // ============================================================

    /**
     * The main method and entry-point into the application.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {
        ClientView clientView = new ClientView();
        SearchView searchView = new SearchView();
        ResultsView resultsView = new ResultsView();
        ClientManager clientManager = new ClientManager();

        // Assigns the controller with the View and Model for the MVC design pattern.
        ClientController clientController = new ClientController(clientView, searchView, resultsView, clientManager);
    }
}