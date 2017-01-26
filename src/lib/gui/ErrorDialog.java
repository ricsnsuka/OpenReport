package lib.gui;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorDialog {
	public static final String INVALID_EMAIL_MESSAGE = "Invalid email added: ";
	
	public static void showErrorDialog(Component component, String message) {
		JOptionPane.showMessageDialog(component,
			    message,
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
}
