package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public final class EBrokerApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8755708701546284324L;
	
	
	public EBrokerApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.eBrokerAppliaction;
		this.hoverHint = "eBroker sites";
		this.attributeToFind = "name";
		build(controller, frame, panel, attributeToFind, gridy);
	}
	
	

}
