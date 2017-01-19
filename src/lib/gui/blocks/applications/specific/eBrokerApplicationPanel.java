package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public final class eBrokerApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8755708701546284324L;
	private final String attributeToFind = "name";
	
	
	public eBrokerApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.eBrokerAppliaction;
		this.hoverHint = "eBroker sites";
		build(config, frame, panel, attributeToFind, gridy);
	}
	
	

}
