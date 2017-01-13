package lib.gui.blocks.applications;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.structs.ReportConfig;

public class ApplicationsPanel {

	public static final String eBrokerAppliaction = "eBroker";
	public static final String activeQuoteApplication = "ActiveQuote";


	private static final String label = "Applications";
	private ArrayList<ApplicationPanel> applicationPanels;
	
	private JPanel panel;

	private JTextField txtSelected;
	private int gridy;


	public ApplicationsPanel(ReportConfig config, JFrame frame) {
		this.gridy = 0;
		applicationPanels = new ArrayList<>();
		buildPanel(frame);
	}


	private void buildPanel(JFrame frame) {
		JPanel applicationPanel = new JPanel();
		applicationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), ApplicationsPanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_applicationPanel = new GridBagConstraints();
		gbc_applicationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_applicationPanel.fill = GridBagConstraints.BOTH;
		gbc_applicationPanel.gridx = 0;
		gbc_applicationPanel.gridy = 3;
		frame.getContentPane().add(applicationPanel, gbc_applicationPanel);
		applicationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel = new JPanel();
		applicationPanel.add(panel);
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
		default:
			System.out.println("Application doesn't exist");
			return;
		}
		gridy++;
		applicationPanels.add(applicationPanel);
	}





}
