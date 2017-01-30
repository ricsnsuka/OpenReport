package lib.controller;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import lib.adapters.EmailAdapter;
import lib.email.EmailManager;
import lib.exceptions.OpenReportException;
import lib.gui.ErrorDialog;
import lib.gui.blocks.email.EmailPanel;
import lib.structs.OpenReportsCache;

public class EmailController {

	private EmailAdapter emailAdapter;
	private EmailPanel emailPanel;
	private OpenReportsCache cache;


	public EmailController(OpenReportsCache cache, EmailAdapter emailAdapter, EmailPanel emailPanel) {
		this.emailAdapter = emailAdapter;
		this.emailPanel = emailPanel;
		this.cache = cache;
	}

	public void setEmailToSupportTeam(boolean value) {
		this.emailAdapter.setToSupport(value);
	}

	public boolean isEmailToSupportTeam() {
		return this.emailAdapter.isToSupport();
	}

	public void addEmailReceiver(String emailAddress) throws OpenReportException {
		String trimmed = emailAddress.trim();
		if(!EmailManager.validateEmailAddress(trimmed)) {
			ErrorDialog.showErrorDialog(this.emailPanel, ErrorDialog.INVALID_EMAIL_MESSAGE + trimmed);
			throw new OpenReportException(ErrorDialog.INVALID_EMAIL_MESSAGE + trimmed);
		}
		if(!getEmailReceiversList().contains(trimmed)) {
			this.emailAdapter.addReceiver(trimmed);
		} 
	}

	public void updateEmailReceivers() throws OpenReportException {
		if(isEmailToSupportTeam()) {
			for(String email : cache.getEmailList()) {
				addEmailReceiver(email);
			}
		}
	}

	public ArrayList<String> getEmailReceiversList() {
		return this.emailAdapter.getReceivers();
	}
	
	public EmailManager getDefaultSupportEmailList() {
		return this.cache.getEmailList();
	}

	public void addSupportEmailReceiver(String emailAddress, Component component) {
		String trimmed = emailAddress.trim();
		if(EmailManager.validateEmailAddress(trimmed)) {
			getDefaultSupportEmailList().addEmail(trimmed);
		} else {
			JOptionPane.showMessageDialog(component, "The email address is invalid.");
		} 
	}

	public void removeSupportEmailReceiver(String emailAddress) {
		getDefaultSupportEmailList().remove(emailAddress);
	}

}
