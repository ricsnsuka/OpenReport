package lib.gui.blocks;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SavePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5674878303750315499L;
	private JButton schedule;
	private JButton runNow;
	
	
	public SavePanel() {
		super();
	}
	
	public void build(JFrame frame) {
		buildPanel(frame);
	}
	
	private void buildPanel(JFrame frame) {
		GridBagConstraints gbc_savePanel = new GridBagConstraints();
		gbc_savePanel.fill = GridBagConstraints.VERTICAL;
		gbc_savePanel.gridx = 0;
		gbc_savePanel.gridy = 5;
		frame.getContentPane().add(this, gbc_savePanel);
		GridBagLayout gbl_savePanel = new GridBagLayout();
		gbl_savePanel.columnWidths = new int[]{65, 65, 0};
		gbl_savePanel.rowHeights = new int[]{20, 0};
		gbl_savePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_savePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gbl_savePanel);

		schedule = new JButton("Schedule");
		schedule.setEnabled(false);
		schedule.setToolTipText("Unavailable for now...");

		GridBagConstraints gbc_btnSchedule = new GridBagConstraints();
		gbc_btnSchedule.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSchedule.insets = new Insets(0, 0, 0, 5);
		gbc_btnSchedule.gridx = 0;
		gbc_btnSchedule.gridy = 0;
		add(schedule, gbc_btnSchedule);

		runNow = new JButton("Run now");
		runNow.setActionCommand("run");
		
		GridBagConstraints gbc_btnRunNow = new GridBagConstraints();
		gbc_btnRunNow.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunNow.gridx = 1;
		gbc_btnRunNow.gridy = 0;
		add(runNow, gbc_btnRunNow);
		
	}
	
	public JButton getSchedule() {
		return schedule;
	}

	public JButton getRunNow() {
		return runNow;
	}
	
	
}
