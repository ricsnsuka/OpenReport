package lib.gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import lib.gui.blocks.EmailPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.gui.blocks.schedule.SchedulePanel;
import lib.gui.blocks.severitytype.SeverityTypePanel;
import lib.structs.ReportConfig;
import test.FileParserTest;

public class OpenReportConfig {

	private JFrame frame;
	private ReportConfig config;

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

		ApplicationsPanel applicationsPanel = new ApplicationsPanel(config, frame);
		
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.eBrokerAppliaction);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.activeQuoteApplication);

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

		


	}


}
