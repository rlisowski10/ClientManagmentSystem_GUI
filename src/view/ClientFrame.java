package view;

import java.awt.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.event.ActionListener;

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

    public ClientFrame(ClientView clientView, SearchView searchView, ResultsView resultsView) {
        clientFrame = new JFrame("Client Manager");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // clientFrame.setLayout(new GridLayout());

        //gbLayout = new GridBagLayout();

        gbConstraints = new GridBagConstraints();
        //gbConstraints.insets = new Insets(1, 1, 1, 1);

        //clientPanel.setLayout(gbLayout);
        JPanel clientPanel = clientView.getPanel();
        addPanelToFrame(clientPanel, 0, 0, 1);

        // clientFrame.pack();
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

        gbConstraints.gridheight = gridHeight;
        //gbConstraints.anchor = GridBagConstraints.SOUTHWEST;

        //gbLayout.setConstraints(component, gbConstraints);
        //clientFrame.getContentPane().add(panel);
        clientFrame.add(panel, gbConstraints);
    }
}