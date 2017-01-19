package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class OpenUnitMeterApplicationPanel extends ApplicationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582485587189601487L;

	private final String attributeToFind = "serverNumber";


	public OpenUnitMeterApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openUnitMeterApplication;
		this.hoverHint = "Open Unit Meter";
		build(config, frame, panel, attributeToFind, gridy);
	}


}
