package lib.gui.blocks.applications.specific;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class OpenDataWarehouseApplicationPanel extends ApplicationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8211310965856813951L;
	private final String attributeToFind = "serverNumber";

	public OpenDataWarehouseApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.openDataWarehouseApplication;
		this.hoverHint = "Open Data Warehouse";
		build(config, frame, panel, attributeToFind, gridy);
	}
}
