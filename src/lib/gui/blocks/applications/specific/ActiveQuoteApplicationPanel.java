package lib.gui.blocks.applications.specific;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.adapters.applications.ApplicationsAdapter;
import lib.exceptions.OpenReportException;
import lib.gui.blocks.applications.ApplicationDialog;
import lib.gui.blocks.applications.ApplicationPanel;
import lib.gui.blocks.applications.ApplicationsPanel;
import lib.structs.ReportConfig;

public class ActiveQuoteApplicationPanel extends ApplicationPanel {
	public static final String AQ_ProductTitle = "ActiveQuote";
	private final String attributeToFind = "serverNumber";
	

	public ActiveQuoteApplicationPanel(ReportConfig config, JFrame frame, JPanel panel, int gridy) {
		super();
		this.label = ApplicationsPanel.activeQuoteApplication;
		addCurrentDialogApplicationData(AQ_ProductTitle, attributeToFind);
		applicationsAdapter = new ApplicationsAdapter();
		try {
			config.setApplications(AQ_ProductTitle, applicationsAdapter);
		}catch(OpenReportException e) {
			
		}
		buildPanel(frame, panel, gridy);
	}

	@Override
	protected void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ApplicationDialog(frame, label, dialogData);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
				addFrameInspector(frame);
			}
		});

	}

}
