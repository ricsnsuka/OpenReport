package lib.gui.blocks.severitytype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.adapters.SeverityTypeAdapter;
import lib.structs.ReportConfig;

public class SeverityTypePanel {
	
	private static final String label = "Severity Type";
	
	private SeverityTypeAdapter severityTypeAdapter;
	
	
	private JCheckBox all;
	private JCheckBox severe;
	private JCheckBox info;
	private JCheckBox warning;
	
	public SeverityTypePanel(ReportConfig config, JFrame frame) {
		severityTypeAdapter = new SeverityTypeAdapter();
		config.setSeverityTypes(severityTypeAdapter);
		buildPanel(frame);
	}
	
	private void buildPanel(JFrame frame) {
		JPanel severityPanel = new JPanel();
		severityPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), SeverityTypePanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_severityPanel = new GridBagConstraints();
		gbc_severityPanel.anchor = GridBagConstraints.NORTH;
		gbc_severityPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_severityPanel.insets = new Insets(0, 0, 5, 0);
		gbc_severityPanel.gridx = 0;
		gbc_severityPanel.gridy = 2;
		frame.getContentPane().add(severityPanel, gbc_severityPanel);
		severityPanel.setLayout(new BorderLayout(0, 0));


		Panel typeChkbxPnl = new Panel();
		severityPanel.add(typeChkbxPnl, BorderLayout.SOUTH);
		GridBagLayout gbl_typeChkbxPnl = new GridBagLayout();
		gbl_typeChkbxPnl.columnWidths = new int[]{15, 70, 70, 70, 70, 0};
		gbl_typeChkbxPnl.rowHeights = new int[]{22, 0};
		gbl_typeChkbxPnl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_typeChkbxPnl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		typeChkbxPnl.setLayout(gbl_typeChkbxPnl);

		severe = new JCheckBox("SEVERE");

		GridBagConstraints gbc_chkbxSevere = new GridBagConstraints();
		gbc_chkbxSevere.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbxSevere.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxSevere.gridx = 1;
		gbc_chkbxSevere.gridy = 0;
		typeChkbxPnl.add(severe, gbc_chkbxSevere);

		info = new JCheckBox("INFO");

		GridBagConstraints gbc_chkbxInfo = new GridBagConstraints();
		gbc_chkbxInfo.anchor = GridBagConstraints.NORTH;
		gbc_chkbxInfo.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxInfo.gridx = 2;
		gbc_chkbxInfo.gridy = 0;
		typeChkbxPnl.add(info, gbc_chkbxInfo);

		warning = new JCheckBox("WARNING");

		GridBagConstraints gbc_chkbxWarning = new GridBagConstraints();
		gbc_chkbxWarning.anchor = GridBagConstraints.NORTH;
		gbc_chkbxWarning.gridx = 3;
		gbc_chkbxWarning.gridy = 0;
		typeChkbxPnl.add(warning, gbc_chkbxWarning);
		
		all = new JCheckBox("ALL");

		GridBagConstraints gbc_chkbxAll = new GridBagConstraints();
		gbc_chkbxAll.anchor = GridBagConstraints.NORTHEAST;
		gbc_chkbxAll.gridx = 4;
		gbc_chkbxAll.gridy = 0;
		typeChkbxPnl.add(all, gbc_chkbxAll);
		
		
		addListeners();
	}
	
	private void addListeners() {
		all.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				boolean selected;
				severityTypeAdapter.setAllTypes((selected = all.isSelected()));
				if(selected) {
					severe.setSelected(true);
					info.setSelected(true);
					warning.setSelected(true);

				} else {
					if(checkAllChecksSelected()) {
						all.setSelected(true);
					}
				}

			}
		});
		severe.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				boolean selected;
				severityTypeAdapter.setSevere((selected = severe.isSelected()));
				if(selected) {
					if(checkAllChecksSelected()) {
						all.setSelected(true);
					}
				} else {
					if(severityTypeAdapter.isAllTypes()) {
						all.setSelected(false);
					}
				}
			}
		});
		info.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean selected;
				severityTypeAdapter.setInfo((selected = info.isSelected()));
				if(selected) {
					if(checkAllChecksSelected()) {
						all.setSelected(true);
					}
				} else {
					if(severityTypeAdapter.isAllTypes()) {
						all.setSelected(false);
					}
				}
			}
		});
		warning.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean selected;
				severityTypeAdapter.setWarning((selected = warning.isSelected()));
				if(selected) {
					if(checkAllChecksSelected()) {
						all.setSelected(true);
					}
				} else {
					if(severityTypeAdapter.isAllTypes()) {
						all.setSelected(false);
					}
				}
			}
		});
	}
	
	private boolean checkAllChecksSelected() {
		return severityTypeAdapter.isSevere() && severityTypeAdapter.isInfo() && severityTypeAdapter.isWarning();
	}
	
	public boolean validatePanel() {
		return severityTypeAdapter.isSevere() || severityTypeAdapter.isInfo() || severityTypeAdapter.isWarning();
	}
}
