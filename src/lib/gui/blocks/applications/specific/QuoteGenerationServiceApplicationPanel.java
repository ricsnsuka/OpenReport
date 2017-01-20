package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class QuoteGenerationServiceApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4959295562301308297L;
	private final String attributeToFind = "serverNumber";


	public QuoteGenerationServiceApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.quoteGenerationServiceApplication;
		this.hoverHint = "Quote Generation Service";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
