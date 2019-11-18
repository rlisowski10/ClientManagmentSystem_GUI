package view;

import java.awt.*;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.util.regex.*;

/**
 * This class provides validation functionality for the user-defined text field
 * data entered into the ClientView.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientTextFieldValidation extends InputVerifier {

    // ============================================================
    // Member Variables
    // ============================================================

    JTextField textField;
    String text;

    // ============================================================
    // Public Instance Methods
    // ============================================================

    /**
     * Provides an implementation for the verfiy method of the InputVerifier
     * abstract class. In this care, this method provides custom validation for text
     * fields within the client panel.
     * 
     * @param input The component from the client panel that is being validated.
     * @return boolean A 'true' value is returned if the text within the text field
     *         meets the validation requirements.
     */
    @Override
    public boolean verify(JComponent input) {
        boolean isInputValid = true;
        textField = (JTextField) input;
        text = textField.getText();

        // Each if-else branch tests for a specific button from the client panel. Each
        // button has been provided with a name, which is checked for in the statements.
        if (textField.getName().equals("firstNameTextField")) {
            isInputValid = maximumLengthValidation(20);
        } else if (textField.getName().equals("lastNameTextField")) {
            isInputValid = maximumLengthValidation(20);
        } else if (textField.getName().equals("addressTextField")) {
            isInputValid = maximumLengthValidation(50);
        } else if (textField.getName().equals("postalCodeTextField")) {
            String regex = "^[A-Z][0-9][A-Z] [0-9][A-Z][0-9]$";
            String toolTip = "Postal code format must be 'A1B 2C3'.";
            isInputValid = regexValidation(regex, toolTip);
        } else if (textField.getName().equals("phoneNumberTextField")) {
            String regex = "^[0-9]{3}[-][0-9]{3}[-][0-9]{4}$";
            String toolTip = "Phone number format must be '123-456-7890'.";
            isInputValid = regexValidation(regex, toolTip);
        }

        return isInputValid;
    }

    /**
     * Checks the maximum length for the text in a text field.
     * 
     * @param maxLength The maximum number of characters of text permitted.
     * @return boolean A 'true' value is returned if the text within the text field
     *         meets the validation requirements.
     */
    public boolean maximumLengthValidation(int maxLength) {
        if (text.length() > maxLength || text.equals("")) {
            // Creates a red border around the text field if the text is not valid.
            textField.setBorder(new LineBorder(Color.red, 2));
            textField.setToolTipText("Maximum length of " + maxLength + " characters.");
            return false;
        } else {
            // Sets the border back to its original look if the text is valid.
            textField.setBorder(new JTextField().getBorder());
            textField.setToolTipText(null);
            return true;
        }
    }

    /**
     * Checks the text in a text field against a regex expression created
     * specifically for that text field.
     * 
     * @param regex   A regex string specific to that text field.
     * @param toolTip A tooltip that contains the error message for the text field.
     * @return boolean A 'true' value is returned if the text within the text field
     *         meets the validation requirements.
     */
    public boolean regexValidation(String regex, String toolTip) {
        if (!Pattern.matches(regex, text)) {
            // Creates a red border around the text field if the text is not valid.
            textField.setBorder(new LineBorder(Color.red, 2));
            textField.setToolTipText(toolTip);
            return false;
        } else {
            // Sets the border back to its original look if the text is valid.
            textField.setBorder(new JTextField().getBorder());
            textField.setToolTipText(null);
            return true;
        }
    }
}