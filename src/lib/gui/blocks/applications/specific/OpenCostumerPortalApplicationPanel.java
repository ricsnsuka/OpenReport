package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class OpenCostumerPortalApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 603856829643365021L;
	private final String attributeToFind = "serverNumber";


	public OpenCostumerPortalApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openCostumerPortalApplication;
		this.hoverHint = "Open Costumer Portal";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
