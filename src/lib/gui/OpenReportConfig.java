package lib.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.gui.blocks.EmailPanel;
import lib.gui.blocks.applications.ActiveQuoteDialog;
import lib.gui.blocks.applications.ApplicationDialog;
import lib.gui.blocks.applications.eBrokerDialog;
import lib.gui.blocks.schedule.SchedulePanel;
import lib.gui.blocks.severitytype.SeverityTypePanel;
import lib.structs.ReportConfig;
import test.FileParserTest;

public class OpenReportConfig {

	private JFrame frame;
	private ReportConfig config;

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
		config = new ReportConfig();
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

		EmailPanel emailPanel = new EmailPanel(frame);

		SeverityTypePanel severityPanel = new SeverityTypePanel(config, frame);


		SchedulePanel schedulePanel = new SchedulePanel(config, frame);


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
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
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
		gbc_lblEbroker.fill = GridBagConstraints.VERTICAL;
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
				//				frame.setEnabled(false);
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
		txtXSelected.setText(((dialog == null)?"0":dialog.getSelectedValues().size()) + " selected");
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
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				//				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}

		});
		GridBagConstraints gbc_btnSelect_1 = new GridBagConstraints();
		gbc_btnSelect_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect_1.gridx = 5;
		gbc_btnSelect_1.gridy = 0;
		panel_5.add(btnSelect_1, gbc_btnSelect_1);

		txtXSelected_1 = new JTextField();
		txtXSelected_1.setText(((dialog == null)?"0":dialog.getSelectedValues().size()) + " selected");
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


				if(!emailPanel.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "You haven't set any destination address.");
				} else if(!severityPanel.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "At least one tipe must be picked.");
				} else {
					schedulePanel.refreshConfigInformation();
					System.out.println(config.getSchedule().getRuntime());
					FileParserTest.run(config);
				}



			}
		});
		GridBagConstraints gbc_btnRunNow = new GridBagConstraints();
		gbc_btnRunNow.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunNow.gridx = 1;
		gbc_btnRunNow.gridy = 0;
		savePanel.add(btnRunNow, gbc_btnRunNow);

		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				txtXSelected.setText(((dialog == null)?"X":dialog.getSelectedValues().size()) + " selected");
				frame.setEnabled(true);
			}
			public void windowLostFocus(WindowEvent arg0) {
				frame.setEnabled(false);
			}
		});


	}


}
