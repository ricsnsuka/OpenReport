package lib.gui.blocks.applications;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lib.adapters.ApplicationsAdapter;
import lib.structs.ReportConfig;

public abstract class ApplicationPanel {
	
	protected String label;
	
	protected ApplicationDialog dialog;
	
	private JTextField txtXSelected;
	private ApplicationsAdapter applicationsAdapter;

	protected abstract void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button);
	
	public ApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, String label, int gridx, int gridy) {
		
		buildPanel(panel, frame, gridx, gridy);
	}
	
	private void buildPanel(JPanel panel, JFrame frame, int gridx, int gridy) {
		JPanel appPanel = new JPanel();
		GridBagConstraints appPanelConstrains = new GridBagConstraints();
		appPanelConstrains.fill = GridBagConstraints.HORIZONTAL;
		appPanelConstrains.insets = new Insets(0, 0, 5, 0);
		appPanelConstrains.gridx = gridx;
		appPanelConstrains.gridy = gridy;
		panel.add(appPanel, appPanelConstrains);
		GridBagLayout appPanelLayout = new GridBagLayout();
		appPanelLayout.columnWidths = new int[]{10, 70, 10, 20, 20, 75, 10, 250, 0};
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

		JCheckBox chkBoxAll = new JCheckBox("All");
		GridBagConstraints chkBoxAllConstraints = new GridBagConstraints();
		chkBoxAllConstraints.insets = new Insets(0, 0, 0, 5);
		chkBoxAllConstraints.gridx = 3;
		chkBoxAllConstraints.gridy = 0;
		appPanel.add(chkBoxAll, chkBoxAllConstraints);

		JButton btnSelect = new JButton("Select...");
		
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 5;
		gbc_btnSelect.gridy = 0;
		appPanel.add(btnSelect, gbc_btnSelect);

		txtXSelected = new JTextField();
		txtXSelected.setEnabled(false);
		txtXSelected.setEditable(false);
		txtXSelected.setText(((dialog == null)?"0":dialog.getSelectedValues().size()) + " selected");
		GridBagConstraints gbc_txtXSelected = new GridBagConstraints();
		gbc_txtXSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected.gridx = 7;
		gbc_txtXSelected.gridy = 0;
		appPanel.add(txtXSelected, gbc_txtXSelected);
		txtXSelected.setColumns(10);
	}
	/*
	 * btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new eBrokerDialog(frame);
				//				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}
		});
	 */
	
}
