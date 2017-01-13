package lib.gui.blocks.applications;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lib.adapters.applications.ApplicationsAdapter;
import lib.structs.ReportConfig;

public abstract class ApplicationPanel {

	protected String label;
	protected ApplicationDialog dialog;
	protected JTextField txtXSelected;
	protected ApplicationsAdapter applicationsAdapter;
	
	public ApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		
	}

	protected abstract void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button);


	protected void buildPanel(JPanel panel, JFrame frame, int gridy) {
		JPanel appPanel = new JPanel();
		GridBagConstraints appPanelConstrains = new GridBagConstraints();
		appPanelConstrains.fill = GridBagConstraints.HORIZONTAL;
		appPanelConstrains.insets = new Insets(0, 0, 5, 0);
		appPanelConstrains.gridx = 0;
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
		addListenerToSelectButton(frame, panel, btnSelect);

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
	
	protected void addFrameInspector(JFrame frame) {
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				txtXSelected.setText(((dialog == null)?"X":dialog.getSelectedValues().size()) + " selected");
				applicationsAdapter.setSelectedValues(dialog.getSelectedValues());
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
	}
	
	

}
