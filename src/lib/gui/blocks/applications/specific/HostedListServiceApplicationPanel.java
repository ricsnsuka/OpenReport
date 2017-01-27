package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class HostedListServiceApplicationPanel extends ApplicationPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 2656969883245160409L;


	public HostedListServiceApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.hostedListServiceApplication;
		this.hoverHint = "Hosted List Service";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
