package lib.gui.blocks.applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.adapters.applications.eBrokerApplicationAdapter;
import lib.structs.ReportConfig;

public class eBrokerApplicationPanel extends ApplicationPanel {
	public static final String eBroker_ProductTitle = "eBroker";
	private final String attributeToFind = "name";
	
	
	public eBrokerApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super(config, frame, panel, gridy);
		this.label = ApplicationsPanel.eBrokerAppliaction;
		applicationsAdapter = new eBrokerApplicationAdapter();
		config.setApplications(applicationsAdapter);
		buildPanel(panel, frame, gridy);
	}

	@Override
	protected void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ApplicationDialog(frame, label, eBroker_ProductTitle, attributeToFind);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
				addFrameInspector(frame);
			}
		});
	}

}
