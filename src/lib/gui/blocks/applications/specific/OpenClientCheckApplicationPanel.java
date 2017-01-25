package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class OpenClientCheckApplicationPanel extends ApplicationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 270055137555116890L;

	private final String attributeToFind = "serverNumber";


	public OpenClientCheckApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openClientCheckApplication;
		this.hoverHint = "Open Client Check";
		build(config, frame, panel, attributeToFind, gridy);
	}


}