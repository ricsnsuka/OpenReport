package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class OpenCostumerPortalApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 603856829643365021L;


	public OpenCostumerPortalApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openCostumerPortalApplication;
		this.hoverHint = "Open Costumer Portal";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
