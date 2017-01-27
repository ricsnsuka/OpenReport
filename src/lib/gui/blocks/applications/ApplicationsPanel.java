package lib.gui.blocks.applications;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.controller.ApplicationController;
import lib.exceptions.OpenReportException;
import lib.structs.ReportConfig;

public class ApplicationsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String eBrokerAppliaction = "eBroker";
	public static final String activeQuoteApplication = "ActiveQuote";
	public static final String openQuoteApplication = "OpenQuote";
	public static final String openCostumerPortalApplication = "OCP";
	public static final String enrichmentHUBApplication = "eHUB";
	public static final String hostedListServiceApplication = "HostedLists";
	public static final String openClientCheckApplication = "OCC";
	public static final String openDataWarehouseApplication = "ODW";
	public static final String openUnitMeterApplication = "UnitMeter";
	public static final String rteDeployerApplication = "RTEDeployer";
	public static final String quoteGenerationServiceApplication = "QGS";
	public static final String activeQuote4PowerplaceApplication = "AQ4PP";

	private static final String label = "Applications";
	
	
	private JPanel panel;

	private int gridy;


	public ApplicationsPanel() {
		super();
		this.gridy = 0;
		
	}
	
	public void build(JFrame frame) {
		buildPanel(frame);
	}


	private void buildPanel(JFrame frame) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), ApplicationsPanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_applicationPanel = new GridBagConstraints();
		gbc_applicationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_applicationPanel.fill = GridBagConstraints.BOTH;
		gbc_applicationPanel.gridx = 0;
		gbc_applicationPanel.gridy = 1;
		frame.getContentPane().add(this, gbc_applicationPanel);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{525, 0};
		gbl_panel_3.rowHeights = new int[]{45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel_3);

	}
	

	public void addNewApplicationPanel(ApplicationController controller, ReportConfig config, JFrame frame, String label) {
		try {
			controller.createApplicationPanel(config, frame, label, panel, gridy);
			gridy++;
		} catch (OpenReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}





}
