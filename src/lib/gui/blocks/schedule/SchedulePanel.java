package lib.gui.blocks.schedule;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.controller.ScheduleController;

public class SchedulePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -698270896475404291L;
	private static final String label = "Schedule";

	public void build(JFrame frame, ScheduleController scheduleController) {
		buildFrame(frame, scheduleController);
	}

	private void buildFrame(JFrame frame, ScheduleController scheduleController) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), SchedulePanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_schedulePanel = new GridBagConstraints();
//		gbc_schedulePanel.anchor = GridBagConstraints.NORTH;
		gbc_schedulePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_schedulePanel.insets = new Insets(0, 0, 5, 0);
		gbc_schedulePanel.gridx = 0;
		gbc_schedulePanel.gridy = 3;
		frame.getContentPane().add(this, gbc_schedulePanel);
		GridBagLayout gbl_schedulePanel = new GridBagLayout();
		gbl_schedulePanel.columnWidths = new int[]{10, 300, 70, 10, 0};
		gbl_schedulePanel.rowHeights = new int[]{20, 20, 0};
		gbl_schedulePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_schedulePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_schedulePanel);

		JPanel weekPanel = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(weekPanel, gbc_panel_1);

		ScheduleWeekdayButton[] weekdays = new ScheduleWeekdayButton[]{new ScheduleWeekdayButton("Sunday", "SUN"),
				new ScheduleWeekdayButton("Monday", "MON"), new ScheduleWeekdayButton("Tuesday", "TUE"),
				new ScheduleWeekdayButton("Wednesday", "WED"), new ScheduleWeekdayButton("Thursday", "THU"),
				new ScheduleWeekdayButton("Friday", "FRI"), new ScheduleWeekdayButton("Saturday", "SAT")
		};

		for(ScheduleWeekdayButton weekdayToggle: weekdays) {
			weekPanel.add(weekdayToggle);
			weekdayToggle.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(weekdayToggle.isSelected()) {
						scheduleController.addSelectedWeekday(weekdayToggle.getWeekDay());
					} else {
						scheduleController.removeSelectedWeekday(weekdayToggle.getWeekDay());
					}
				}
			});
		}

		JPanel runTimePanel = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		add(runTimePanel, gbc_panel_2);

		JLabel runtimeLbl = new JLabel("Run-time");
		runTimePanel.add(runtimeLbl);

		Choice runtime = new Choice();
		runtime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				scheduleController.setRunTimeHour(runtime.getSelectedItem());
			}
		});
		for(int i = 0; i < 24; i++) {
			runtime.add((i<10?"0":"") + i + ":00");
		}
		runTimePanel.add(runtime);
		
		
		JPanel bottomLane = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(bottomLane, gbc_panel);

		JCheckBox weekly = new JCheckBox("Weekly");
		weekly.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				scheduleController.setWeeklyReport(weekly.isSelected());
			}
		});
		bottomLane.add(weekly);

	}
	
	@Override
	public void setEnabled(boolean enable) {
		super.setEnabled(enable);
		setEnabled(this, enable);
		
	}
	
	private void setEnabled(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for(Component component : components) {
			component.setEnabled(enable);
			if(component instanceof Container) {
				setEnabled((Container) component, enable);
			}
		}
	}

}
