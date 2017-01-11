package lib.gui.blocks.applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActiveQuoteApplicationPanel extends ApplicationPanel {

	public ActiveQuoteApplicationPanel() {
		super();
		label = "Active Quote";
	}

	@Override
	protected void addListenerToSelectButton(JFrame frame, JPanel containerPanel, JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new ActiveQuoteDialog(frame);
				frame.setEnabled(false);
				dialog.getDialog().setVisible(true);
			}
		});

	}

}
