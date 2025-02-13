package lib.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import lib.adapters.ApplicationAdapter;
import lib.adapters.EmailAdapter;
import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.controller.ApplicationController;
import lib.controller.EmailController;
import lib.controller.ScheduleController;
import lib.controller.SeverityController;
import lib.exceptions.OpenReportException;
import lib.exec.OpenReporter;
import lib.gui.blocks.SavePanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.gui.blocks.email.EmailPanel;
import lib.gui.blocks.schedule.SchedulePanel;
import lib.gui.blocks.severitytype.SeverityTypePanel;
import lib.structs.OpenReportsCache;
import lib.structs.ReportConfig;

public class OpenReportConfig {
	private ReportConfig config;
	private JFrame frame;
	private int reportNumber;

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
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OpenReportConfig.class.getResource("/resources/img/ogi.png")));

		frame.setBounds(100, 100, 600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("OpenReports");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{10, 300, 50, 100, 40, 35, 0};
		//APP-300 SEV-50 SCH-100 SEND-40
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		config = new ReportConfig();
		OpenReportsCache cache = new OpenReportsCache();
		
		ApplicationsPanel applicationsPanel = new ApplicationsPanel();
		applicationsPanel.build(frame);
		
		String[] applications = new String[]{ApplicationsPanel.eBrokerAppliaction,  ApplicationsPanel.activeQuoteApplication,
				ApplicationsPanel.activeQuote4PowerplaceApplication, ApplicationsPanel.enrichmentHUBApplication,
				ApplicationsPanel.hostedListServiceApplication, ApplicationsPanel.openClientCheckApplication,
				ApplicationsPanel.openDataWarehouseApplication, ApplicationsPanel.openCostumerPortalApplication,
				ApplicationsPanel.openQuoteApplication, ApplicationsPanel.openUnitMeterApplication,
				ApplicationsPanel.quoteGenerationServiceApplication, ApplicationsPanel.rteDeployerApplication};

		for(String application: applications) {
			applicationsPanel.addNewApplicationPanel(new ApplicationController(new ApplicationAdapter()), config, frame, application);
		}

		//----------------SEVERITY-----------------
		SeverityTypeAdapter severityAdapter = new SeverityTypeAdapter();
		SeverityTypePanel severityPanel = new SeverityTypePanel();
		SeverityController severityController = new SeverityController(config, severityPanel, severityAdapter);
		
		severityController.buildPanel(frame);
		
		//-----------------------------------------
		
		//----------------SCHEDULE-----------------
		
		ScheduleAdapter scheduleAdpater = new ScheduleAdapter();
		SchedulePanel schedulePanel = new SchedulePanel();
		ScheduleController scheduleController = new ScheduleController(config, schedulePanel, scheduleAdpater);
		
		scheduleController.buildPanel(frame);
		
		//-----------------------------------------
		
		//--------------SEND_TO BLOCK--------------
		
		EmailAdapter emailAdapter = new EmailAdapter();
		EmailPanel emailPanel = new EmailPanel();
		EmailController emailController = new EmailController(cache, emailAdapter, emailPanel);
		
		emailController.buildPanel(frame);
		
		//-----------------------------------------
		
		SavePanel savePanel = new SavePanel();
		savePanel.build(frame);
		
		savePanel.getRunNow().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!emailPanel.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "You haven't set any destination address.");
				} else if(config.countApplications() <= 0) {
					ErrorDialog.showErrorDialog(frame, "Nothing to report.\nNo application selected.");
				} else if(!severityController.validatePanel()) {
					ErrorDialog.showErrorDialog(frame, "You must pick at least one type.");
				} else {
					try {
						emailController.updateEmailReceivers();
					} catch (OpenReportException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					OpenReporter report = new OpenReporter(config.clone(), emailController, "Report-" + reportNumber);
					report.start();
					reportNumber++;
				}
			}
		});
		
		frame.getRootPane().setDefaultButton(savePanel.getRunNow());
		frame.pack();
	}
}
