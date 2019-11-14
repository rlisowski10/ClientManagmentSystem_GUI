package view;

import java.awt.*;
import javax.swing.*;

public class ClientFrame {

    // ============================================================
    // Member Variables
    // ============================================================

    private JFrame clientFrame;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;

    // ============================================================
    // Constructors
    // ============================================================

    // TODO: Clean this up into methods
    public ClientFrame(ClientView clientView, SearchView searchView, ResultsView resultsView) {
        clientFrame = new JFrame("Client Manager");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setLayout(new GridBagLayout());
        gbConstraints = new GridBagConstraints();
   
        addPanelToFrame(searchView.getPanel(), 0, 0, 1);
        addPanelToFrame(clientView.getPanel(), 1, 0, 1);

        clientFrame.pack();
        clientFrame.setVisible(true);
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void addPanelToFrame(JPanel panel, int gridXPos, int gridYPos, int gridHeight) {
        gbConstraints.gridx = gridXPos;
        gbConstraints.gridy = gridYPos;
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.gridheight = gridHeight;

        clientFrame.add(panel, gbConstraints);
    }
}