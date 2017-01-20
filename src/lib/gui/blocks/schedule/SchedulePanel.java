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

import lib.adapters.ScheduleAdapter;
import lib.structs.ReportConfig;

public class SchedulePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -698270896475404291L;
	private static final String label = "Schedule";

	private ScheduleAdapter scheduleAdapter;

	
	public SchedulePanel(ReportConfig config) {
		super();
		scheduleAdapter = new ScheduleAdapter();
		config.setSchedule(scheduleAdapter);
	}
	
	public void build(JFrame frame) {
		buildFrame(frame);
	}

	private void buildFrame(JFrame frame) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), SchedulePanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_schedulePanel = new GridBagConstraints();
		gbc_schedulePanel.anchor = GridBagConstraints.NORTH;
		gbc_schedulePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_schedulePanel.insets = new Insets(0, 0, 5, 0);
		gbc_schedulePanel.gridx = 0;
		gbc_schedulePanel.gridy = 3;
		frame.getContentPane().add(this, gbc_schedulePanel);
		GridBagLayout gbl_schedulePanel = new GridBagLayout();
		gbl_schedulePanel.columnWidths = new int[]{300, 70, 0};
		gbl_schedulePanel.rowHeights = new int[]{35, 35, 0};
		gbl_schedulePanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_schedulePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_schedulePanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);

		JCheckBox weekly = new JCheckBox("Weekly");
		weekly.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				scheduleAdapter.setWeekly(weekly.isSelected());
			}
		});
		panel.add(weekly);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);

		ScheduleWeekdayButton[] weekdays = new ScheduleWeekdayButton[]{new ScheduleWeekdayButton("Sunday", "SUN"),
				new ScheduleWeekdayButton("Monday", "MON"), new ScheduleWeekdayButton("Tuesday", "TUE"),
				new ScheduleWeekdayButton("Wednesday", "WED"), new ScheduleWeekdayButton("Thursday", "THU"),
				new ScheduleWeekdayButton("Friday", "FRI"), new ScheduleWeekdayButton("Saturday", "SAT")
		};

		for(ScheduleWeekdayButton weekdayToggle: weekdays) {
			panel_1.add(weekdayToggle);
			weekdayToggle.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(weekdayToggle.isSelected()) {
						scheduleAdapter.getWeekdays().add(weekdayToggle.getWeekDay());
					} else {
						scheduleAdapter.getWeekdays().remove(weekdayToggle.getWeekDay());
					}
				}
			});
		}

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);

		JLabel runtimeLbl = new JLabel("Run-time");
		panel_2.add(runtimeLbl);

		Choice runtime = new Choice();
		runtime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				scheduleAdapter.setRuntime(runtime.getSelectedItem());
			}
		});
		for(int i = 0; i < 24; i++) {
			runtime.add((i<10?"0":"") + i + ":00");
		}
		panel_2.add(runtime);

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
