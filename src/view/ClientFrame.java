package view;

import java.awt.*;
import javax.swing.*;

/**
 * This class holds the user interface frame for the application. Panels are
 * added to the frame, and are laid-out using a GridBagLayout.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientFrame {

    // ============================================================
    // Member Variables
    // ============================================================

    private JFrame clientFrame;
    private GridBagConstraints gbConstraints;

    // ============================================================
    // Constructors
    // ============================================================

    /**
     * Constructor that creates the new user interface frame, the GridBagLayout, and
     * the GridBagConstraints objects.
     * 
     * @param clientView  The client information panel for the user interface.
     * @param searchView  The client search panel for the user interface.
     * @param resultsView The results viewer panel for the user interface.
     */
    public ClientFrame(ClientView clientView, SearchView searchView, ResultsView resultsView) {
        clientFrame = new JFrame("Client Manager");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setLayout(new GridBagLayout());
        gbConstraints = new GridBagConstraints();

        setupPanels(clientView, searchView, resultsView);
        setupFrame();
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    /**
     * Set up the frame display options.
     */
    private void setupFrame() {
        clientFrame.pack();
        clientFrame.setResizable(false);
        clientFrame.setVisible(true);
    }

    /**
     * Sets up the panels by sending them to a helper method for laying them out in
     * the GridBagLayout.
     * 
     * @param clientView  The client information panel for the user interface.
     * @param searchView  The client search panel for the user interface.
     * @param resultsView The results viewer panel for the user interface.
     */
    private void setupPanels(ClientView clientView, SearchView searchView, ResultsView resultsView) {
        addPanelToFrame(searchView.getPanel(), 0, 0, 1);
        addPanelToFrame(resultsView.getResultsPanel(), 0, 1, 1);
        addPanelToFrame(clientView.getPanel(), 1, 0, 2);
    }

    /**
     * Adds each panel to the frame.
     * 
     * @param panel      The user-interface panel to be displayed in the frame.
     * @param gridXPos   The x-position in the layout grid.
     * @param gridYPos   The y-position in the layout grid.
     * @param gridHeight The number of grid rows that that the panel can cross.
     */
    private void addPanelToFrame(JPanel panel, int gridXPos, int gridYPos, int gridHeight) {
        gbConstraints.gridx = gridXPos;
        gbConstraints.gridy = gridYPos;
        gbConstraints.gridheight = gridHeight;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;

        clientFrame.add(panel, gbConstraints);
    }
}