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

import lib.controller.ApplicationController;
import lib.structs.Application;

public class ApplicationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ApplicationDialog dialog;
	private JTextField txtXSelected;
	
	protected String label;
	protected String hoverHint;
	protected String attributeToFind;


	private void addFrameInspector(ApplicationController controller, JFrame frame) {
		frame.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				controller.setSelectedValues(controller.getDialogSelectedList());
				txtXSelected.setText(((controller.getSelectedValues() == null)?"0":controller.numberOfSelectedValues()) + " selected");
			}

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				//DO NOTHING
			}
		});
	}

	private void addListenerToChkBoxAll(ApplicationController controller, JCheckBox chkBoxAll) {
		chkBoxAll.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chkBoxAll.isSelected()) {
					txtXSelected.setText(controller.getAllValues().size() + " selected");
					controller.setSelectedValues(controller.getAllValues());
				} else {
					txtXSelected.setText(((controller.getDialogSelectedList() == null)?"0":controller.getDialogSelectedList().size()) + " selected");
					controller.setSelectedValues((controller.getDialogSelectedList() == null)?(new ArrayList<Application>()):controller.getDialogSelectedList());
				}
			}
		});
	}

	private void addListenerToSelectButton(ApplicationController controller, JFrame frame, JButton button, JCheckBox checkBox) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBox.setSelected(false);
				openDialog(frame);
				addFrameInspector(controller, frame);
			}
			
			private void openDialog(JFrame frame) {
				changeDialogPosition(frame);
				frame.setEnabled(false);
				dialog.setVisible(true);
			}
			
			private void changeDialogPosition(JFrame frame) {
				dialog.setBounds(frame.getX()+frame.getWidth(), frame.getY(), dialog.getWidth(), dialog.getHeight());
			}
		});
	}
	
	

	private void buildPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		GridBagConstraints appPanelConstrains = new GridBagConstraints();
		appPanelConstrains.fill = GridBagConstraints.HORIZONTAL;
		appPanelConstrains.insets = new Insets(0, 0, 5, 0);
		appPanelConstrains.gridx = 0;
		appPanelConstrains.gridy = gridy;
		panel.add(this, appPanelConstrains);
		GridBagLayout appPanelLayout = new GridBagLayout();
		appPanelLayout.columnWidths = new int[]{10, 80, 10, 75, 20, 15, 10, 65, 10, 175, 0};
		appPanelLayout.rowHeights = new int[]{14, 0};
		appPanelLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		appPanelLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(appPanelLayout);

		JLabel lblLabel = new JLabel(label);
		lblLabel.setToolTipText(hoverHint);
		GridBagConstraints lblLabelConstraints = new GridBagConstraints();
		lblLabelConstraints.fill = GridBagConstraints.VERTICAL;
		lblLabelConstraints.insets = new Insets(0, 0, 0, 5);
		lblLabelConstraints.gridx = 1;
		lblLabelConstraints.gridy = 0;
		add(lblLabel, lblLabelConstraints);

		JButton btnSelect = new JButton("Select...");
		

		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 3;
		gbc_btnSelect.gridy = 0;
		add(btnSelect, gbc_btnSelect);

		JCheckBox chkBoxAll = new JCheckBox("All");

		GridBagConstraints chkBoxAllConstraints = new GridBagConstraints();
		chkBoxAllConstraints.insets = new Insets(0, 0, 0, 5);
		chkBoxAllConstraints.gridx = 5;
		chkBoxAllConstraints.gridy = 0;
		add(chkBoxAll, chkBoxAllConstraints);

		txtXSelected = new JTextField();
		txtXSelected.setEnabled(false);
		txtXSelected.setEditable(false);
		txtXSelected.setText(((controller.getSelectedValues() == null)?"0":controller.getSelectedValues().size()) + " selected");
		GridBagConstraints gbc_txtXSelected = new GridBagConstraints();
		gbc_txtXSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected.gridx = 9;
		gbc_txtXSelected.gridy = 0;
		add(txtXSelected, gbc_txtXSelected);
		txtXSelected.setColumns(10);
		
		addListenerToSelectButton(controller, frame, btnSelect, chkBoxAll);
		addListenerToChkBoxAll(controller, chkBoxAll);
	}
	
	protected void build(ApplicationController controller, JFrame frame, JPanel panel, String attributeToFind, int gridy) {
		buildPanel(controller, frame, panel, gridy);
		dialog = controller.createApplicationDialog(frame, label, attributeToFind);
	}
	
	public ApplicationPanel() {
		super();
		attributeToFind = "serverNumber";
	}
	
	public String getAttributeToFind() {
		return this.attributeToFind;
	}
	
	public String getLable() {
		return this.label;
	}


}
