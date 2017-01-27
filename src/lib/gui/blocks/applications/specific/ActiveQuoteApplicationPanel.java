package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public final class ActiveQuoteApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8635927568366053853L;
	

	public ActiveQuoteApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.activeQuoteApplication;
		this.hoverHint = "Active Quote";
		build(controller, frame, panel, attributeToFind, gridy);
	}

}
