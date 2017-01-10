package lib.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.adapters.ApplicationsAdapter;
import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.gui.blocks.applications.ActiveQuoteDialog;
import lib.gui.blocks.applications.ApplicationDialog;
import lib.gui.blocks.applications.eBrokerDialog;
import lib.structs.ReportConfig;
import test.FileParserTest;

public class OpenReportConfig {

	private JFrame frame;
	private ReportConfig config;
	private SeverityTypeAdapter severityTypeAdapter;
	private ScheduleAdapter scheduleController;
	private ApplicationsAdapter applicationsController;
	private JTextField txtXSelected;
	private JTextField txtXSelected_1;
	private ApplicationDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenReportConfig window = new OpenReportConfig();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenReportConfig() {
//		eBrokerFrame = new eBrokerDialog();
//		eBrokerFrame.setVisible(false);
		
		severityTypeAdapter = new SeverityTypeAdapter();
		scheduleController = new ScheduleAdapter();
		applicationsController = new ApplicationsAdapter();
		config = new ReportConfig(severityTypeAdapter, scheduleController, applicationsController);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("OpenReports");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{530, 0};
		gridBagLayout.rowHeights = new int[]{39, 50, 100, 250, 35, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		Panel emailSetup = new Panel();
		GridBagConstraints gbc_emailSetup = new GridBagConstraints();
		gbc_emailSetup.insets = new Insets(0, 0, 5, 0);
		gbc_emailSetup.gridx = 0;
		gbc_emailSetup.gridy = 0;
		frame.getContentPane().add(emailSetup, gbc_emailSetup);
		GridBagLayout gbl_emailSetup = new GridBagLayout();
		gbl_emailSetup.columnWidths = new int[]{0, 51, 87, 75, 15, 275, 0, 0};
		gbl_emailSetup.rowHeights = new int[] {22, 0};
		gbl_emailSetup.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_emailSetup.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		emailSetup.setLayout(gbl_emailSetup);

		Label sendTo = new Label("Send to");
		GridBagConstraints gbc_sendTo = new GridBagConstraints();
		gbc_sendTo.anchor = GridBagConstraints.NORTHWEST;
		gbc_sendTo.insets = new Insets(0, 0, 0, 5);
		gbc_sendTo.gridx = 1;
		gbc_sendTo.gridy = 0;
		emailSetup.add(sendTo, gbc_sendTo);

		JCheckBox checkbox = new JCheckBox("Support DEVs");
		checkbox.setSelected(true);
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.anchor = GridBagConstraints.NORTHWEST;
		gbc_checkbox.insets = new Insets(0, 0, 0, 5);
		gbc_checkbox.gridx = 2;
		gbc_checkbox.gridy = 0;
		emailSetup.add(checkbox, gbc_checkbox);

		JButton btnEdit = new JButton("Edit...");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 3;
		gbc_btnEdit.gridy = 0;
		emailSetup.add(btnEdit, gbc_btnEdit);

		JPanel emailOthersText = new JPanel();
		GridBagConstraints gbc_emailOthersText = new GridBagConstraints();
		gbc_emailOthersText.insets = new Insets(0, 0, 0, 5);
		gbc_emailOthersText.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailOthersText.gridx = 5;
		gbc_emailOthersText.gridy = 0;
		emailSetup.add(emailOthersText, gbc_emailOthersText);
		emailOthersText.setLayout(new BorderLayout(0, 0));

		Label Others = new Label("Others");
		emailOthersText.add(Others);

		TextField emailOthers = new TextField();
		emailOthersText.add(emailOthers, BorderLayout.EAST);
		emailOthers.setColumns(35);

		JPanel severityPanel = new JPanel();
		severityPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Severity Type", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_severityPanel = new GridBagConstraints();
		gbc_severityPanel.anchor = GridBagConstraints.NORTH;
		gbc_severityPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_severityPanel.insets = new Insets(0, 0, 5, 0);
		gbc_severityPanel.gridx = 0;
		gbc_severityPanel.gridy = 1;
		frame.getContentPane().add(severityPanel, gbc_severityPanel);
		severityPanel.setLayout(new BorderLayout(0, 0));

		JPanel allChkbxPnl = new JPanel();
		severityPanel.add(allChkbxPnl, BorderLayout.WEST);
		GridBagLayout gbl_allChkbxPnl = new GridBagLayout();
		gbl_allChkbxPnl.columnWidths = new int[]{15, 70, 0};
		gbl_allChkbxPnl.rowHeights = new int[]{22, 0};
		gbl_allChkbxPnl.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_allChkbxPnl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		allChkbxPnl.setLayout(gbl_allChkbxPnl);

		JCheckBox chkbxAll = new JCheckBox("ALL");

		GridBagConstraints gbc_chkbxAll = new GridBagConstraints();
		gbc_chkbxAll.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbxAll.gridx = 1;
		gbc_chkbxAll.gridy = 0;
		allChkbxPnl.add(chkbxAll, gbc_chkbxAll);

		Panel typeChkbxPnl = new Panel();
		severityPanel.add(typeChkbxPnl, BorderLayout.SOUTH);
		GridBagLayout gbl_typeChkbxPnl = new GridBagLayout();
		gbl_typeChkbxPnl.columnWidths = new int[]{15, 70, 70, 70, 0};
		gbl_typeChkbxPnl.rowHeights = new int[]{22, 0};
		gbl_typeChkbxPnl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_typeChkbxPnl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		typeChkbxPnl.setLayout(gbl_typeChkbxPnl);

		JCheckBox chkbxSevere = new JCheckBox("SEVERE");

		GridBagConstraints gbc_chkbxSevere = new GridBagConstraints();
		gbc_chkbxSevere.anchor = GridBagConstraints.NORTHWEST;
		gbc_chkbxSevere.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxSevere.gridx = 1;
		gbc_chkbxSevere.gridy = 0;
		typeChkbxPnl.add(chkbxSevere, gbc_chkbxSevere);

		JCheckBox chkbxInfo = new JCheckBox("INFO");

		GridBagConstraints gbc_chkbxInfo = new GridBagConstraints();
		gbc_chkbxInfo.anchor = GridBagConstraints.NORTH;
		gbc_chkbxInfo.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxInfo.gridx = 2;
		gbc_chkbxInfo.gridy = 0;
		typeChkbxPnl.add(chkbxInfo, gbc_chkbxInfo);

		JCheckBox chkbxWarning = new JCheckBox("WARNING");

		GridBagConstraints gbc_chkbxWarning = new GridBagConstraints();
		gbc_chkbxWarning.anchor = GridBagConstraints.NORTHEAST;
		gbc_chkbxWarning.gridx = 3;
		gbc_chkbxWarning.gridy = 0;
		typeChkbxPnl.add(chkbxWarning, gbc_chkbxWarning);
		
		
		
		chkbxAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chkbxAll.isSelected()) {
					severityTypeAdapter.setAllTypes(true);
					chkbxSevere.setSelected(true);
					chkbxInfo.setSelected(true);
					chkbxWarning.setSelected(true);

				} else {
					severityTypeAdapter.setAllTypes(false);
				}

			}
		});
		chkbxSevere.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chkbxSevere.isSelected()) {
					severityTypeAdapter.setSevere(true);
				} else {
					severityTypeAdapter.setSevere(false);
					if(severityTypeAdapter.isAllTypes()) {
						chkbxAll.setSelected(false);
					}
				}
			}
		});
		chkbxInfo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkbxInfo.isSelected()) {
					severityTypeAdapter.setInfo(true);
				} else {
					severityTypeAdapter.setInfo(false);
					if(severityTypeAdapter.isAllTypes()) {
						chkbxAll.setSelected(false);
					}
				}
			}
		});
		chkbxWarning.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chkbxWarning.isSelected()) {
					severityTypeAdapter.setWarning(true);
				} else {
					severityTypeAdapter.setWarning(false);
					if(severityTypeAdapter.isAllTypes()) {
						chkbxAll.setSelected(false);
					}
				}
			}
		});


		JPanel schedulePanel = new JPanel();
		schedulePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Schedule", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_schedulePanel = new GridBagConstraints();
		gbc_schedulePanel.anchor = GridBagConstraints.NORTH;
		gbc_schedulePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_schedulePanel.insets = new Insets(0, 0, 5, 0);
		gbc_schedulePanel.gridx = 0;
		gbc_schedulePanel.gridy = 2;
		frame.getContentPane().add(schedulePanel, gbc_schedulePanel);
		GridBagLayout gbl_schedulePanel = new GridBagLayout();
		gbl_schedulePanel.columnWidths = new int[]{15, 70, 450, 0};
		gbl_schedulePanel.rowHeights = new int[]{35, 35, 0};
		gbl_schedulePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_schedulePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		schedulePanel.setLayout(gbl_schedulePanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		schedulePanel.add(panel, gbc_panel);

		JCheckBox chkbxWeekly = new JCheckBox("Weekly");
		panel.add(chkbxWeekly);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		schedulePanel.add(panel_1, gbc_panel_1);

		JToggleButton tglbtnSun = new JToggleButton("SUN");
		tglbtnSun.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnSun.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnSun.setToolTipText("Run on Sundays");

		JToggleButton tglbtnMon = new JToggleButton("MON");
		tglbtnMon.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnMon.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnMon.setToolTipText("Run on Mondays\r\n");

		JToggleButton tglbtnTue = new JToggleButton("TUE");
		tglbtnTue.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnTue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnTue.setToolTipText("Run on Tuesdays");

		JToggleButton tglbtnWed = new JToggleButton("WED");
		tglbtnWed.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnWed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnWed.setToolTipText("Run on Wednesdays");

		JToggleButton tglbtnThu = new JToggleButton("THU");
		tglbtnThu.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnThu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnThu.setToolTipText("Run on Thursdays");

		JToggleButton tglbtnFrid = new JToggleButton("FRI");
		tglbtnFrid.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnFrid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnFrid.setToolTipText("Run on Fridays");

		JToggleButton tglbtnSat = new JToggleButton("SAT");
		tglbtnSat.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnSat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnSat.setToolTipText("Run on Saturdays");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(tglbtnSun);
		panel_1.add(tglbtnMon);
		panel_1.add(tglbtnTue);
		panel_1.add(tglbtnWed);
		panel_1.add(tglbtnThu);
		panel_1.add(tglbtnFrid);
		panel_1.add(tglbtnSat);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		schedulePanel.add(panel_2, gbc_panel_2);

		Label runtime = new Label("Run-time");
		panel_2.add(runtime);

		Choice choice = new Choice();
		panel_2.add(choice);

		JPanel applicationPanel = new JPanel();
		applicationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Applications", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_applicationPanel = new GridBagConstraints();
		gbc_applicationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_applicationPanel.fill = GridBagConstraints.BOTH;
		gbc_applicationPanel.gridx = 0;
		gbc_applicationPanel.gridy = 3;
		frame.getContentPane().add(applicationPanel, gbc_applicationPanel);
		applicationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_3 = new JPanel();
		applicationPanel.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{525, 0};
		gbl_panel_3.rowHeights = new int[]{45, 45, 45, 45, 45, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{10, 70, 10, 20, 20, 75, 10, 250, 0};
		gbl_panel_4.rowHeights = new int[]{14, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblEbroker = new JLabel("eBroker");
		GridBagConstraints gbc_lblEbroker = new GridBagConstraints();
		gbc_lblEbroker.insets = new Insets(0, 0, 0, 5);
		gbc_lblEbroker.gridx = 1;
		gbc_lblEbroker.gridy = 0;
		panel_4.add(lblEbroker, gbc_lblEbroker);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("All");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 3;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_4.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JButton btnSelect = new JButton("Select...");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new eBrokerDialog(frame);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}
		});
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 5;
		gbc_btnSelect.gridy = 0;
		panel_4.add(btnSelect, gbc_btnSelect);
		
		txtXSelected = new JTextField();
		txtXSelected.setEnabled(false);
		txtXSelected.setEditable(false);
		txtXSelected.setText((dialog == null)?"X":dialog.getSelectedValues().size() + " selected");
		GridBagConstraints gbc_txtXSelected = new GridBagConstraints();
		gbc_txtXSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected.gridx = 7;
		gbc_txtXSelected.gridy = 0;
		panel_4.add(txtXSelected, gbc_txtXSelected);
		txtXSelected.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		panel_3.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{10, 70, 10, 20, 20, 75, 10, 250, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblActivequote = new JLabel("ActiveQuote");
		GridBagConstraints gbc_lblActivequote = new GridBagConstraints();
		gbc_lblActivequote.insets = new Insets(0, 0, 0, 5);
		gbc_lblActivequote.gridx = 1;
		gbc_lblActivequote.gridy = 0;
		panel_5.add(lblActivequote, gbc_lblActivequote);
		
		JCheckBox chckbxAll = new JCheckBox("All");
		GridBagConstraints gbc_chckbxAll = new GridBagConstraints();
		gbc_chckbxAll.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxAll.gridx = 3;
		gbc_chckbxAll.gridy = 0;
		panel_5.add(chckbxAll, gbc_chckbxAll);
		
		JButton btnSelect_1 = new JButton("Select...");
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ActiveQuoteDialog(frame);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}
				
		});
		GridBagConstraints gbc_btnSelect_1 = new GridBagConstraints();
		gbc_btnSelect_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect_1.gridx = 5;
		gbc_btnSelect_1.gridy = 0;
		panel_5.add(btnSelect_1, gbc_btnSelect_1);
		
		txtXSelected_1 = new JTextField();
		txtXSelected_1.setText((dialog == null)?"X":dialog.getSelectedValues().size() + " selected");
		txtXSelected_1.setEnabled(false);
		txtXSelected_1.setEditable(false);
		GridBagConstraints gbc_txtXSelected_1 = new GridBagConstraints();
		gbc_txtXSelected_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXSelected_1.gridx = 7;
		gbc_txtXSelected_1.gridy = 0;
		panel_5.add(txtXSelected_1, gbc_txtXSelected_1);
		txtXSelected_1.setColumns(10);

		Panel savePanel = new Panel();
		GridBagConstraints gbc_savePanel = new GridBagConstraints();
		gbc_savePanel.gridx = 0;
		gbc_savePanel.gridy = 4;
		frame.getContentPane().add(savePanel, gbc_savePanel);
		GridBagLayout gbl_savePanel = new GridBagLayout();
		gbl_savePanel.columnWidths = new int[]{59, 63, 0};
		gbl_savePanel.rowHeights = new int[]{22, 0};
		gbl_savePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_savePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		savePanel.setLayout(gbl_savePanel);

		Button btnSchedule = new Button("Schedule");
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnSchedule = new GridBagConstraints();
		gbc_btnSchedule.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSchedule.insets = new Insets(0, 0, 0, 5);
		gbc_btnSchedule.gridx = 0;
		gbc_btnSchedule.gridy = 0;
		savePanel.add(btnSchedule, gbc_btnSchedule);

		Button btnRunNow = new Button("RUN NOW");
		btnRunNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileParserTest.run(config);
			}
		});
		GridBagConstraints gbc_btnRunNow = new GridBagConstraints();
		gbc_btnRunNow.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunNow.gridx = 1;
		gbc_btnRunNow.gridy = 0;
		savePanel.add(btnRunNow, gbc_btnRunNow);
	}
	

}
