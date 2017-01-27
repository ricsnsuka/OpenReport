package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class EnrinchmentHUBApplicationPanel extends ApplicationPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5069740383744078748L;
	

	
	public EnrinchmentHUBApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.enrichmentHUBApplication;
		this.hoverHint = "Enrichment HUB";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
