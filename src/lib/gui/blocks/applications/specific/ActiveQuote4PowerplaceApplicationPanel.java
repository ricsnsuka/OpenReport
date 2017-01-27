package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class ActiveQuote4PowerplaceApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4614852094085886774L;


	public ActiveQuote4PowerplaceApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.activeQuote4PowerplaceApplication;
		this.hoverHint = "Active Quote 4 Powerplace";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
