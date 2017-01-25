package lib.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import lib.exec.OpenReporter;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.gui.blocks.email.EmailPanel;
import lib.gui.blocks.schedule.SchedulePanel;
import lib.gui.blocks.severitytype.SeverityTypePanel;
import lib.structs.OpenReportsCache;
import lib.structs.ReportConfig;
import java.awt.Toolkit;

public class OpenReportConfig {
	private ReportConfig config;
	private JFrame frame;
	private int reportNumber;
	private OpenReportsCache cache;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					try {
						for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					    UIManager.setLookAndFeel("Metal");
					}
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
		reportNumber = 1;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OpenReportConfig.class.getResource("/resources/img/ogi.png")));

		frame.setBounds(100, 100, 600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("OpenReports");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{530, 0};
		gridBagLayout.rowHeights = new int[]{10, 300, 50, 100, 40, 35, 0};
		//APP-250 SEV-50 SCH-100 SEND-40
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		config = new ReportConfig();
		cache = new OpenReportsCache();
		
		ApplicationsPanel applicationsPanel = new ApplicationsPanel();
		applicationsPanel.build(frame);

		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.eBrokerAppliaction);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.activeQuoteApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.activeQuote4PowerplaceApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.enrichmentHUBApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.hostedListServiceApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.openClientCheckApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.openDataWarehouseApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.openCostumerPortalApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.openQuoteApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.openUnitMeterApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.rteDeployerApplication);
		applicationsPanel.addNewApplicationPanel(config, frame, ApplicationsPanel.quoteGenerationServiceApplication);

		SeverityTypePanel severityPanel = new SeverityTypePanel(config);
		severityPanel.build(frame);

		SchedulePanel schedulePanel = new SchedulePanel(config);
		schedulePanel.build(frame);
		schedulePanel.setEnabled(false);
		
		EmailPanel emailPanel = new EmailPanel();
		emailPanel.build(frame, cache);
		
		JPanel savePanel = new JPanel();
		GridBagConstraints gbc_savePanel = new GridBagConstraints();
		gbc_savePanel.fill = GridBagConstraints.VERTICAL;
		gbc_savePanel.gridx = 0;
		gbc_savePanel.gridy = 5;
		frame.getContentPane().add(savePanel, gbc_savePanel);
		GridBagLayout gbl_savePanel = new GridBagLayout();
		gbl_savePanel.columnWidths = new int[]{59, 63, 0};
		gbl_savePanel.rowHeights = new int[]{22, 0};
		gbl_savePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_savePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		savePanel.setLayout(gbl_savePanel);

		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.setEnabled(false);
		btnSchedule.setToolTipText("Unavailable for now...");

		GridBagConstraints gbc_btnSchedule = new GridBagConstraints();
		gbc_btnSchedule.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSchedule.insets = new Insets(0, 0, 0, 5);
		gbc_btnSchedule.gridx = 0;
		gbc_btnSchedule.gridy = 0;
		savePanel.add(btnSchedule, gbc_btnSchedule);

		JButton btnRunNow = new JButton("Run now");
		btnRunNow.setActionCommand("run");
		
		btnRunNow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!emailPanel.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "You haven't set any destination address.");
				} else if(config.countApplications() <= 0) {
					ErrorDialog.showErrorDialog(frame, "Nothing to report.");
				} else if(!severityPanel.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "At least one tipe must be picked.");
				} else {
					ReportConfig threadConfig = config.clone();
					OpenReporter report = new OpenReporter(threadConfig, "Report-" + reportNumber);
					report.start();
					reportNumber++;
				}
			}
		});
		frame.getRootPane().setDefaultButton(btnRunNow);
		GridBagConstraints gbc_btnRunNow = new GridBagConstraints();
		gbc_btnRunNow.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunNow.gridx = 1;
		gbc_btnRunNow.gridy = 0;
		savePanel.add(btnRunNow, gbc_btnRunNow);

		frame.pack();
	}
}
