package lib.gui.blocks.applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.adapters.applications.eBrokerApplicationAdapter;
import lib.structs.ReportConfig;

public class ActiveQuoteApplicationPanel extends ApplicationPanel {

	public ActiveQuoteApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super(config, frame, panel, gridy);
		this.label = ApplicationsPanel.activeQuoteApplication;
		applicationsAdapter = new eBrokerApplicationAdapter();
		config.setApplications(applicationsAdapter);
		buildPanel(panel, frame, gridy);
	}

	@Override
	protected void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ActiveQuoteDialog(frame);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
				addFrameInspector(frame);
			}
		});

	}

}
