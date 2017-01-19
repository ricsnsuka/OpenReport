package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public final class OpenQuoteApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4529449261042131948L;
	private final String attributeToFind = "serverNumber";
	
	
	public OpenQuoteApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openQuoteApplication;
		this.hoverHint = "Open Quote";
		build(config, frame, panel, attributeToFind, gridy);
	}
	
}
