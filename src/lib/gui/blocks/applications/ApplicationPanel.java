package lib.gui.blocks.applications;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class ApplicationPanel {
	
	protected String label;
	
	private JPanel applicationPanel;
	protected ApplicationDialog dialog;
	private JTextField txtSelected;
	
	
	public ApplicationPanel() {
		applicationPanel = new JPanel();
	}
	
	
	public void addApplicationPanel(JFrame frame, JPanel containerPanel, int gridx, int gridy) {
		GridBagConstraints gbc_applicationPanel = new GridBagConstraints();
		gbc_applicationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_applicationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_applicationPanel.gridx = gridx;
		gbc_applicationPanel.gridy = gridy;
		containerPanel.add(applicationPanel, gbc_applicationPanel);
		GridBagLayout gbl_applicationPanel = new GridBagLayout();
		gbl_applicationPanel.columnWidths = new int[]{10, 70, 10, 20, 20, 75, 10, 230, 0};
		gbl_applicationPanel.rowHeights = new int[]{14, 0};
		gbl_applicationPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_applicationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		applicationPanel.setLayout(gbl_applicationPanel);
		
		JLabel lblEbroker = new JLabel(label);
		GridBagConstraints gbc_lblEbroker = new GridBagConstraints();
		gbc_lblEbroker.fill = GridBagConstraints.VERTICAL;
		gbc_lblEbroker.insets = new Insets(0, 0, 0, 5);
		gbc_lblEbroker.gridx = 1;
		gbc_lblEbroker.gridy = 0;
		applicationPanel.add(lblEbroker, gbc_lblEbroker);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("All");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 3;
		gbc_chckbxNewCheckBox.gridy = 0;
		applicationPanel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		
		JButton btnSelect = new JButton("Select...");
		addListenerToSelectButton(frame, containerPanel, btnSelect);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 5;
		gbc_btnSelect.gridy = 0;
		containerPanel.add(btnSelect, gbc_btnSelect);
		
		txtSelected = new JTextField();
		txtSelected.setEnabled(false);
		txtSelected.setEditable(false);
		txtSelected.setText(((dialog == null)?"X":dialog.getSelectedValues().size()) + " selected");
		GridBagConstraints gbc_txtXSelected = new GridBagConstraints();
		gbc_txtXSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected.gridx = 7;
		gbc_txtXSelected.gridy = 0;
		applicationPanel.add(txtSelected, gbc_txtXSelected);
	}
	
	protected abstract void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button);
	
	
}
