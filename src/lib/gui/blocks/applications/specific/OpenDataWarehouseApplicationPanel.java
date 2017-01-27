package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class OpenDataWarehouseApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8211310965856813951L;

	public OpenDataWarehouseApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openDataWarehouseApplication;
		this.hoverHint = "Open Data Warehouse";
		build(controller, frame, panel, attributeToFind, gridy);
	}
}
