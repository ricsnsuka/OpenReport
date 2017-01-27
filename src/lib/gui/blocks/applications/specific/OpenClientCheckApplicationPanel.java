package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.controller.ApplicationController;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;

public class OpenClientCheckApplicationPanel extends ApplicationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 270055137555116890L;



	public OpenClientCheckApplicationPanel(ApplicationController controller, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openClientCheckApplication;
		this.hoverHint = "Open Client Check";
		build(controller, frame, panel, attributeToFind, gridy);
	}


}
