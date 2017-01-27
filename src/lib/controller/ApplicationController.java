package lib.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import lib.adapters.ApplicationAdapter;
import lib.exceptions.OpenReportException;
import lib.fileparser.XMLParser;
import lib.gui.ErrorDialog;
import lib.gui.blocks.applications.ApplicationDialog;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationPanelFactory;
import lib.structs.Application;
import lib.structs.ReportConfig;

public class ApplicationController {
	
	private ApplicationAdapter applicationAdapter;
	private ApplicationPanel applicationPanel;
	private ApplicationDialogController dialogController;
	
	private ArrayList<Application> dialogData;
	
	
	public ApplicationController(ApplicationAdapter applicationAdapter) {
		this.applicationAdapter = applicationAdapter;
		dialogData = new ArrayList<>();
		
	}
	
	public void createApplicationPanel(ReportConfig config, JFrame frame, String label, JPanel panel, int gridy) throws OpenReportException {
		applicationPanel = ApplicationPanelFactory.generateApplicationPanel(this, frame, panel, label, gridy);
		try {
			config.setApplications(label, this.applicationAdapter);
		}catch(OpenReportException e) {
			ErrorDialog.showErrorDialog(frame, e.getMessage());
			return;
		}
	}
	
	public ApplicationDialog createApplicationDialog(JFrame frame, String label, String atrributeToFind) {
		addCurrentDialogApplicationData(label, atrributeToFind);
		dialogController = new ApplicationDialogController();
		return new ApplicationDialog(frame, label, this); 
	}
	
	
	private void addCurrentDialogApplicationData(String applicationName, String attributeToFind) {
		XMLParser parser = new XMLParser("src\\resources\\applications.xml");
		for(String attributeValue : parser.getAttributeValues(applicationName, attributeToFind)) {
			dialogData.add(new Application(applicationName, attributeValue));
		}
	}
	
	public ArrayList<Application> getAllValues() {
		return this.dialogData;
	}
	
	public void setSelectedValues(List<Application> values) {
		this.applicationAdapter.setSelectedValues(values);
	}
	
	public List<Application> getSelectedValues() {
		return this.applicationAdapter.getSelectedValues();
	}
	
	public int numberOfSelectedValues() {
		return this.applicationAdapter.getSelectedValues().size();
	}
	
	public DefaultListModel<String> getApplicationListModel() {
		return this.dialogController.createApplicationListModel();
	}
	
	public void setDialogSelectedList(JList<String> selectedItems) {
		this.dialogController.setSelectedValuesList(selectedItems);
	}
	
	public List<Application> getDialogSelectedList() {
		return this.dialogController.getSelectedValuesList();
	}
	
	private class ApplicationDialogController {
		private List<Application> selectedValues;
		
		protected DefaultListModel<String> createApplicationListModel() {
			DefaultListModel<String> listModel = new DefaultListModel<>();
			for(Application value : dialogData) {
				listModel.addElement(value.getName());
			}
			return listModel;
		}
		
		protected List<Application> getSelectedValuesList() {
			return this.selectedValues;
		}
		
		protected void setSelectedValuesList(JList<String> selectedItems) {
			selectedValues = new ArrayList<>();
			for(String value : selectedItems.getSelectedValuesList()) {
				selectedValues.add(new Application(applicationPanel.getLable(), value));
			}
		}
		
	}
	
	
}
