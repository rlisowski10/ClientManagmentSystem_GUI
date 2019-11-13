package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView {

    // ============================================================
    // Member Variables
    // ============================================================

    private JPanel clientPanel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbConstraints;
    private JLabel clientInfoLabel;

    // ============================================================
    // Constructors
    // ============================================================

    public ClientView() {
        // TODO: Add to setupClientPanel method
        clientPanel = new JPanel();
        gbLayout = new GridBagLayout();

        gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(1, 1, 1, 1);

        clientPanel.setLayout(gbLayout);

        // TODO: Add to createClientControls method
        clientInfoLabel = new JLabel("Client Information");

        // TODO: Add to addClientControls method
        addComponentToPanel(clientInfoLabel, 0, 0, 0, 0, 1);
        addComponentToPanel(clientInfoLabel, 1, 0, 0, 0, 1);
    }

    // ============================================================
    // Public Instance Methods
    // ============================================================

    public JPanel getPanel() {
        return clientPanel;
    }

    // ============================================================
    // Private Instance Methods
    // ============================================================

    private void addComponentToPanel(Component component, int gridXPos, int gridYPos, int iPadX, int iPadY,
            int gridHeight) {
        gbConstraints.gridx = gridXPos;
        gbConstraints.gridy = gridYPos;

        gbConstraints.ipadx = iPadX;
        gbConstraints.ipady = iPadY;
        gbConstraints.gridheight = gridHeight;
        gbConstraints.anchor = GridBagConstraints.SOUTHWEST;

        gbLayout.setConstraints(component, gbConstraints);
        clientPanel.add(component);
    }
}