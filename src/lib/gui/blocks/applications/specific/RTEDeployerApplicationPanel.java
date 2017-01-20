package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class RTEDeployerApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9043000198514711281L;
	private final String attributeToFind = "serverNumber";


	public RTEDeployerApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.rteDeployerApplication;
		this.hoverHint = "RTE Deployer";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
