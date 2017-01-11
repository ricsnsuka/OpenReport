package lib.gui.blocks.schedule;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import lib.adapters.ScheduleAdapter;
import lib.structs.ReportConfig;

public class SchedulePanel {
	private static final String label = "Schedule";
	
	private ScheduleAdapter scheduleAdapter;
	private JPanel mainContainer;
	
	private JCheckBox weekly;
	private ScheduleWeekdayButton[] weekdays;
	private Choice runtime;
	
	public SchedulePanel(ReportConfig config, JFrame frame) {
		scheduleAdapter = new ScheduleAdapter();
		config.setSchedule(scheduleAdapter);
		buildFrame(frame);
	}
	
	private void buildFrame(JFrame frame) {
		mainContainer = new JPanel();
		mainContainer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), SchedulePanel.label, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_schedulePanel = new GridBagConstraints();
		gbc_schedulePanel.anchor = GridBagConstraints.NORTH;
		gbc_schedulePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_schedulePanel.insets = new Insets(0, 0, 5, 0);
		gbc_schedulePanel.gridx = 0;
		gbc_schedulePanel.gridy = 2;
		frame.getContentPane().add(mainContainer, gbc_schedulePanel);
		GridBagLayout gbl_schedulePanel = new GridBagLayout();
		gbl_schedulePanel.columnWidths = new int[]{15, 70, 450, 0};
		gbl_schedulePanel.rowHeights = new int[]{35, 35, 0};
		gbl_schedulePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_schedulePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainContainer.setLayout(gbl_schedulePanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		mainContainer.add(panel, gbc_panel);

		weekly = new JCheckBox("Weekly");
		panel.add(weekly);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		mainContainer.add(panel_1, gbc_panel_1);
		
		weekdays = new ScheduleWeekdayButton[]{new ScheduleWeekdayButton("Sunday", "SUN"),
				new ScheduleWeekdayButton("Monday", "MON"), new ScheduleWeekdayButton("Tueday", "TUE"),
				new ScheduleWeekdayButton("Wednesday", "WED"), new ScheduleWeekdayButton("Thursday", "THU"),
				new ScheduleWeekdayButton("Friday", "FRI"), new ScheduleWeekdayButton("Saturday", "SAT")
		};
		
		for(ScheduleWeekdayButton weekdayToggle: weekdays) {
			panel_1.add(weekdayToggle.getToggleButton());
		}
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		mainContainer.add(panel_2, gbc_panel_2);

		Label runtimeLbl = new Label("Run-time");
		panel_2.add(runtimeLbl);
		
		runtime = new Choice();
		for(int i = 0; i < 24; i++) {
			runtime.add((i<10?"0":"") + i + ":00");
		}
		panel_2.add(runtime);
		
	}
	
	public void refreshConfigInformation() {
		System.out.print("Refreshing... ");
		scheduleAdapter.setWeekly(weekly.isSelected());
		scheduleAdapter.setWeekdays(setWeekdaysArray());
		scheduleAdapter.setRuntime(runtime.getSelectedItem());
		System.out.println("DONE!");
	}
	
	private ArrayList<String> setWeekdaysArray() {
		ArrayList<String> ret = new ArrayList<>();
		for(ScheduleWeekdayButton button : weekdays) {
			if(button.getToggleButton().isSelected()) {
				ret.add(button.getWeekDay());
			}
		}
		return ret;
	}
	
	
}
