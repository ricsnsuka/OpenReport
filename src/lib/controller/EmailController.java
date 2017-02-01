package lib.controller;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lib.adapters.EmailAdapter;
import lib.email.EmailManager;
import lib.exceptions.OpenReportException;
import lib.gui.ErrorDialog;
import lib.gui.blocks.email.EmailPanel;
import lib.structs.OpenReportsCache;

public class EmailController {

	private EmailAdapter adapter;
	private EmailPanel panel;
	private OpenReportsCache cache;


	public EmailController(OpenReportsCache cache, EmailAdapter adapter, EmailPanel panel) {
		this.adapter = adapter;
		this.panel = panel;
		this.cache = cache;
	}

	public void setEmailToSupportTeam(boolean value) {
		this.adapter.setToSupport(value);
	}

	public boolean isEmailToSupportTeam() {
		return this.adapter.isToSupport();
	}

	public void addEmailReceiver(String emailAddress) throws OpenReportException {
		String trimmed = emailAddress.trim();
		if(!EmailManager.validateEmailAddress(trimmed)) {
			ErrorDialog.showErrorDialog(this.panel, ErrorDialog.INVALID_EMAIL_MESSAGE + trimmed);
			throw new OpenReportException(ErrorDialog.INVALID_EMAIL_MESSAGE + trimmed);
		}
		if(!getEmailReceiversList().contains(trimmed)) {
			this.adapter.addReceiver(trimmed);
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
		return this.adapter.getReceivers();
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
	
	public void buildPanel(JFrame frame) {
		this.panel.build(frame, this);
	}

}
