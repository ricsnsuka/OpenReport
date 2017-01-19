package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public final class ActiveQuoteApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8635927568366053853L;
	private final String attributeToFind = "serverNumber";
	

	public ActiveQuoteApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.activeQuoteApplication;
		this.hoverHint = "Active Quote";
		build(config, frame, panel, attributeToFind, gridy);
	}

}
