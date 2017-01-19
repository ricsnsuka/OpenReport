package lib.gui.blocks.applications;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.gui.blocks.applications.specific.ActiveQuoteApplicationPanel;
import lib.gui.blocks.applications.specific.EnrinchmentHUBApplicationPanel;
import lib.gui.blocks.applications.specific.HostedListServiceApplicationPanel;
import lib.gui.blocks.applications.specific.OpenClientCheckApplicationPanel;
import lib.gui.blocks.applications.specific.OpenCostumerPortalApplicationPanel;
import lib.gui.blocks.applications.specific.OpenDataWarehouseApplicationPanel;
import lib.gui.blocks.applications.specific.OpenQuoteApplicationPanel;
import lib.gui.blocks.applications.specific.OpenUnitMeterApplicationPanel;
import lib.gui.blocks.applications.specific.eBrokerApplicationPanel;
import lib.structs.ReportConfig;

public class ApplicationsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String eBrokerAppliaction = "eBroker";
	public static final String activeQuoteApplication = "ActiveQuote";
	public static final String openQuoteApplication = "OpenQuote";
	public static final String openCostumerPortalApplication = "OCP";
	public static final String enrichmentHUBApplication = "eHUB";
	public static final String hostedListServiceApplication = "HSL";
	public static final String openClientCheckApplication = "OCC";
	public static final String openDataWarehouseApplication = "ODW";
	public static final String openUnitMeterApplication = "UnitMeter";

	private static final String label = "Applications";
	
	private ArrayList<ApplicationPanel> applicationPanels;
	
	private JPanel panel;

	private int gridy;


	public ApplicationsPanel(JFrame frame) {
		super();
		this.gridy = 0;
		applicationPanels = new ArrayList<>();
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
//		GridBagLayout gbl_applicationsPanel = new GridBagLayout();
//		gbl_applicationsPanel.columnWidths = new int[]{0};
//		gbl_applicationsPanel.rowHeights = new int[]{0};
//		gbl_applicationsPanel.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_applicationsPanel.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel = new JPanel();
		add(panel);
//		GridBagConstraints gbc_panel = new GridBagConstraints();
//		gbc_panel.fill = GridBagConstraints.VERTICAL;
//		add(panel, gbc_panel);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{525, 0};
		gbl_panel_3.rowHeights = new int[]{45, 45, 45, 45, 45, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel_3);

	}


	public void addNewApplicationPanel(ReportConfig config, JFrame frame, String label) {
		ApplicationPanel applicationPanel;
		switch(label) {
		case eBrokerAppliaction:
			applicationPanel = new eBrokerApplicationPanel(config, frame, panel, gridy);
			break;
		case activeQuoteApplication:
			applicationPanel = new ActiveQuoteApplicationPanel(config, frame, panel, gridy);
			break;
		case openQuoteApplication:
			applicationPanel = new OpenQuoteApplicationPanel(config, frame, panel, gridy);
			break;
		case openCostumerPortalApplication:
			applicationPanel = new OpenCostumerPortalApplicationPanel(config, frame, panel, gridy);
			break;
		case enrichmentHUBApplication:
			applicationPanel = new EnrinchmentHUBApplicationPanel(config, frame, panel, gridy);
			break;
		case hostedListServiceApplication:
			applicationPanel = new HostedListServiceApplicationPanel(config, frame, panel, gridy);
			break;
		case openClientCheckApplication:
			applicationPanel = new OpenClientCheckApplicationPanel(config, frame, panel, gridy);
			break;
		case openDataWarehouseApplication:
			applicationPanel = new OpenDataWarehouseApplicationPanel(config, frame, panel, gridy);
			break;
		case openUnitMeterApplication:
			applicationPanel = new OpenUnitMeterApplicationPanel(config, frame, panel, gridy);
			break;
		default:
			System.out.println("Application doesn't exist");
			return;
		}
		gridy++;
		applicationPanels.add(applicationPanel);
	}





}
