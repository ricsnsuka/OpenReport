package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class OpenUnitMeterApplicationPanel extends ApplicationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582485587189601487L;

	public OpenUnitMeterApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openUnitMeterApplication;
		this.hoverHint = "Open Unit Meter";
		build(controller, frame, panel, attributeToFind, gridy);
	}


}
