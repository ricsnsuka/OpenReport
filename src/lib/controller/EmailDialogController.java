package lib.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import lib.email.EmailManager;
import lib.gui.blocks.email.EmailDialog;
import lib.structs.OpenReportsCache;

public class EmailDialogController {
	
	private EmailDialog emailDialog;
	private OpenReportsCache cache;
	
	public EmailDialogController(OpenReportsCache cache, EmailDialog emailDialog) {
		this.cache = cache;
		this.emailDialog = emailDialog;
	}
	
	public ArrayList<String> getDefaultSupportEmailList() {
		return this.cache.getEmailList();
	}

	public void addSupportEmailReceiver(String emailAddress) {
		String trimmed = emailAddress.trim();
		if(EmailManager.validateEmailAddress(trimmed)) {
			getDefaultSupportEmailList().add(trimmed);
		} else {
			JOptionPane.showMessageDialog(emailDialog, "The email address is invalid.");
		} 
	}

	public void removeSupportEmailReceiver(String emailAddress) {
		getDefaultSupportEmailList().remove(emailAddress);
	}
}
