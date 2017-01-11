package lib.gui.blocks.applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class eBrokerApplicationPanel extends ApplicationPanel {

	public eBrokerApplicationPanel() {
		super();
		label = "eBroker";
	}

	@Override
	protected void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new eBrokerDialog(frame);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}
		});
	}

}
