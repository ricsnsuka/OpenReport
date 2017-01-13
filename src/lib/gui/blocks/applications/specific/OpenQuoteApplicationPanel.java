package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public final class OpenQuoteApplicationPanel extends ApplicationPanel {
	private final String attributeToFind = "serverNumber";
	
	
	public OpenQuoteApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openQuoteApplication;
		build(config, frame, panel, attributeToFind, gridy);
	}
	
}
