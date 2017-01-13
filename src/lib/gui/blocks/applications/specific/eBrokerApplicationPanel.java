package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public final class eBrokerApplicationPanel extends ApplicationPanel {
	private final String attributeToFind = "name";
	
	
	public eBrokerApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.eBrokerAppliaction;
		build(config, frame, panel, attributeToFind, gridy);
	}
	
	

}
