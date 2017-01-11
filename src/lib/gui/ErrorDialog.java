package lib.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog {
	
	
	public static void showErrorDialog(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame,
			    message,
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
}
