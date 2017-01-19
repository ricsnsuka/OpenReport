package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class EnrinchmentHUBApplicationPanel extends ApplicationPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5069740383744078748L;
	private final String attributeToFind = "serverNumber";
	

	
	public EnrinchmentHUBApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.enrichmentHUBApplication;
		this.hoverHint = "Enrichment HUB";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
