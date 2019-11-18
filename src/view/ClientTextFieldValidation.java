package view;

import java.awt.*;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.util.regex.*;

public class ClientTextFieldValidation extends InputVerifier {

    JTextField textField;
    String text;

    @Override
    public boolean verify(JComponent input) {
        boolean isInputValid = true;
        textField = (JTextField) input;
        text = textField.getText();

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

    public boolean maximumLengthValidation(int maxLength) {
        if (text.length() > maxLength || text.equals("")) {
            textField.setBorder(new LineBorder(Color.red, 2));
            textField.setToolTipText("Maximum length of " + maxLength + " characters.");
            return false;
        } else {
            textField.setBorder(new JTextField().getBorder());
            textField.setToolTipText(null);
            return true;
        }
    }

    public boolean regexValidation(String regex, String toolTip) {
        if (!Pattern.matches(regex, text)) {
            textField.setBorder(new LineBorder(Color.red, 2));
            textField.setToolTipText(toolTip);
            return false;
        } else {
            textField.setBorder(new JTextField().getBorder());
            textField.setToolTipText(null);
            return true;
        }
    }
}