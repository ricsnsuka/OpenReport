package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class ActiveQuote4PowerplaceApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4614852094085886774L;
	private final String attributeToFind = "serverNumber";


	public ActiveQuote4PowerplaceApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.activeQuote4PowerplaceApplication;
		this.hoverHint = "Active Quote 4 Powerplace";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
