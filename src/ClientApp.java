import controller.ClientController;
import model.ClientManager;
import view.ClientView;

public class ClientApp {

    // ============================================================
    // Static Methods
    // ============================================================

    public static void main(String[] args) {
        ClientView clientView = new ClientView();
        ClientManager clientManager = new ClientManager();

        // Assigns the controller with the View and Model for the MVC design pattern.
        ClientController clientController = new ClientController(clientView, clientManager);
    }
}