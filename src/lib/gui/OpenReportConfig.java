package lib.gui;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Panel;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Button;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;

public class OpenReportConfig {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{634, 0};
		gridBagLayout.rowHeights = new int[]{39, 50, 100, 0, 35, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		Panel emailSetup = new Panel();
		GridBagConstraints gbc_emailSetup = new GridBagConstraints();
		gbc_emailSetup.insets = new Insets(0, 0, 5, 0);
		gbc_emailSetup.gridx = 0;
		gbc_emailSetup.gridy = 0;
		frame.getContentPane().add(emailSetup, gbc_emailSetup);
		GridBagLayout gbl_emailSetup = new GridBagLayout();
		gbl_emailSetup.columnWidths = new int[]{51, 87, 281, 0};
		gbl_emailSetup.rowHeights = new int[] {22, 0};
		gbl_emailSetup.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_emailSetup.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		emailSetup.setLayout(gbl_emailSetup);

		Label sendTo = new Label("Send to");
		GridBagConstraints gbc_sendTo = new GridBagConstraints();
		gbc_sendTo.anchor = GridBagConstraints.NORTHWEST;
		gbc_sendTo.insets = new Insets(0, 0, 0, 5);
		gbc_sendTo.gridx = 0;
		gbc_sendTo.gridy = 0;
		emailSetup.add(sendTo, gbc_sendTo);

		Checkbox checkbox = new Checkbox("Support DEVs");
		checkbox.setState(true);
		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.anchor = GridBagConstraints.NORTHWEST;
		gbc_checkbox.insets = new Insets(0, 0, 0, 5);
		gbc_checkbox.gridx = 1;
		gbc_checkbox.gridy = 0;
		emailSetup.add(checkbox, gbc_checkbox);

		JPanel emailOthersText = new JPanel();
		GridBagConstraints gbc_emailOthersText = new GridBagConstraints();
		gbc_emailOthersText.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailOthersText.gridx = 2;
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

		Checkbox chkbxAll = new Checkbox("ALL");
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

		Checkbox chkbxSevere = new Checkbox("SEVERE");
		GridBagConstraints gbc_chkbxSevere = new GridBagConstraints();
		gbc_chkbxSevere.anchor = GridBagConstraints.NORTH;
		gbc_chkbxSevere.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxSevere.gridx = 1;
		gbc_chkbxSevere.gridy = 0;
		typeChkbxPnl.add(chkbxSevere, gbc_chkbxSevere);

		Checkbox chkbxInfo = new Checkbox("INFO");
		GridBagConstraints gbc_chkbxInfo = new GridBagConstraints();
		gbc_chkbxInfo.anchor = GridBagConstraints.NORTH;
		gbc_chkbxInfo.insets = new Insets(0, 0, 0, 5);
		gbc_chkbxInfo.gridx = 2;
		gbc_chkbxInfo.gridy = 0;
		typeChkbxPnl.add(chkbxInfo, gbc_chkbxInfo);

		Checkbox chkbxWarning = new Checkbox("WARNING");
		GridBagConstraints gbc_chkbxWarning = new GridBagConstraints();
		gbc_chkbxWarning.anchor = GridBagConstraints.NORTH;
		gbc_chkbxWarning.gridx = 3;
		gbc_chkbxWarning.gridy = 0;
		typeChkbxPnl.add(chkbxWarning, gbc_chkbxWarning);




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
		gbl_schedulePanel.columnWidths = new int[]{15, 64, 450, 0};
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

		Checkbox chkbxWeekly = new Checkbox("Weekly");
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
		gbc_applicationPanel.anchor = GridBagConstraints.NORTH;
		gbc_applicationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_applicationPanel.gridx = 0;
		gbc_applicationPanel.gridy = 3;
		frame.getContentPane().add(applicationPanel, gbc_applicationPanel);
		applicationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Panel savePanel = new Panel();
		GridBagConstraints gbc_savePanel = new GridBagConstraints();
		gbc_savePanel.insets = new Insets(0, 0, 5, 0);
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
		GridBagConstraints gbc_btnRunNow = new GridBagConstraints();
		gbc_btnRunNow.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunNow.gridx = 1;
		gbc_btnRunNow.gridy = 0;
		savePanel.add(btnRunNow, gbc_btnRunNow);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
