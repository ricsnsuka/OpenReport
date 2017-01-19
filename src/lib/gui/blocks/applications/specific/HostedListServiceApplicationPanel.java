package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class HostedListServiceApplicationPanel extends ApplicationPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 2656969883245160409L;
	private final String attributeToFind = "serverNumber";


	public HostedListServiceApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.hostedListServiceApplication;
		this.hoverHint = "Hosted List Service";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
