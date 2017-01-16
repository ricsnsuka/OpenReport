package lib.gui.blocks.applications;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lib.adapters.applications.ApplicationsAdapter;
import lib.exceptions.OpenReportException;
import lib.fileparser.XMLParser;
import lib.structs.ReportConfig;

public class ApplicationPanel {

	protected String label;
	protected ApplicationDialog dialog;
	protected JTextField txtXSelected;
	protected ApplicationsAdapter applicationsAdapter;
	protected ArrayList<String> dialogData;
	
	
	
	public ApplicationPanel() {
		dialogData = new ArrayList<>();
	}
	
	protected void build(ReportConfig config, JFrame frame, JPanel panel, String attributeToFind, int gridy) {
		addCurrentDialogApplicationData(label, attributeToFind);
		applicationsAdapter = new ApplicationsAdapter();
		try {
			config.setApplications(label, applicationsAdapter);
		}catch(OpenReportException e) {
			
		}
		buildPanel(frame, panel, gridy);
	}
	
	protected void addListenerToSelectButton(JFrame frame, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ApplicationDialog(frame, label, dialogData);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
				addFrameInspector(frame);
			}
		});
	}
	

	protected void buildPanel(JFrame frame, JPanel panel, int gridy) {
		JPanel appPanel = new JPanel();
		GridBagConstraints appPanelConstrains = new GridBagConstraints();
		appPanelConstrains.fill = GridBagConstraints.HORIZONTAL;
		appPanelConstrains.insets = new Insets(0, 0, 5, 0);
		appPanelConstrains.gridx = 0;
		appPanelConstrains.gridy = gridy;
		panel.add(appPanel, appPanelConstrains);
		GridBagLayout appPanelLayout = new GridBagLayout();
		appPanelLayout.columnWidths = new int[]{10, 75, 10, 75, 20, 15, 10, 250, 0};
		appPanelLayout.rowHeights = new int[]{14, 0};
		appPanelLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		appPanelLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		appPanel.setLayout(appPanelLayout);

		JLabel lblLabel = new JLabel(label);
		GridBagConstraints lblLabelConstraints = new GridBagConstraints();
		lblLabelConstraints.fill = GridBagConstraints.VERTICAL;
		lblLabelConstraints.insets = new Insets(0, 0, 0, 5);
		lblLabelConstraints.gridx = 1;
		lblLabelConstraints.gridy = 0;
		appPanel.add(lblLabel, lblLabelConstraints);

		JButton btnSelect = new JButton("Select...");
		addListenerToSelectButton(frame, btnSelect);

		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 3;
		gbc_btnSelect.gridy = 0;
		appPanel.add(btnSelect, gbc_btnSelect);
		
		JCheckBox chkBoxAll = new JCheckBox("All");
		addListenerToChkBoxAll(chkBoxAll);
		
		GridBagConstraints chkBoxAllConstraints = new GridBagConstraints();
		chkBoxAllConstraints.insets = new Insets(0, 0, 0, 5);
		chkBoxAllConstraints.gridx = 5;
		chkBoxAllConstraints.gridy = 0;
		appPanel.add(chkBoxAll, chkBoxAllConstraints);

		txtXSelected = new JTextField();
		txtXSelected.setEnabled(false);
		txtXSelected.setEditable(false);
		txtXSelected.setText(((applicationsAdapter.getSelectedValues() == null)?"0":applicationsAdapter.getSelectedValues().size()) + " selected");
		GridBagConstraints gbc_txtXSelected = new GridBagConstraints();
		gbc_txtXSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected.gridx = 7;
		gbc_txtXSelected.gridy = 0;
		appPanel.add(txtXSelected, gbc_txtXSelected);
		txtXSelected.setColumns(10);
	}
	
	protected void addFrameInspector(JFrame frame) {
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				applicationsAdapter.setSelectedValues(dialog.getSelectedValues());
				txtXSelected.setText(((applicationsAdapter.getSelectedValues() == null)?"0":applicationsAdapter.getSelectedValues().size()) + " selected");
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
	}
	
	private void addListenerToChkBoxAll(JCheckBox chkBoxAll) {
		chkBoxAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkBoxAll.isSelected()) {
					applicationsAdapter.setSelectedValues(dialogData);
					txtXSelected.setText(applicationsAdapter.getSelectedValues().size() + " selected");
				} else {
					txtXSelected.setText(((dialog == null)?"0":dialog.getSelectedValues().size()) + " selected");
				}
			}
		});
	}
	
	protected void addCurrentDialogApplicationData(String applicationName, String attributeToFind) {
		XMLParser parser = new XMLParser("src\\resources\\applications.xml");
		for(String attributeValue : parser.getAttributeValues(applicationName, attributeToFind)) {
			dialogData.add(attributeValue);
		}
	}
	
	

}
