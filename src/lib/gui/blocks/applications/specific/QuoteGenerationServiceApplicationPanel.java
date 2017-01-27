package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class QuoteGenerationServiceApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4959295562301308297L;


	public QuoteGenerationServiceApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.quoteGenerationServiceApplication;
		this.hoverHint = "Quote Generation Service";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
