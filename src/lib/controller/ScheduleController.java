package lib.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import lib.adapters.ScheduleAdapter;
import lib.gui.blocks.schedule.SchedulePanel;
import lib.structs.ReportConfig;

public class ScheduleController {
	private ScheduleAdapter adapter;
	private SchedulePanel panel;

	public ScheduleController(ReportConfig config, SchedulePanel panel, ScheduleAdapter adapter) {
		this.adapter = adapter;
		this.panel = panel;
		config.setSchedule(adapter);
	}
	
	public void addSelectedWeekday(String weekday) {
		this.adapter.getWeekdays().add(weekday);
	}
	
	public void removeSelectedWeekday(String weekday) {
		this.adapter.getWeekdays().remove(weekday);
	}
	
	public ArrayList<String> getSelectedWeekdays() {
		return this.adapter.getWeekdays();
	}
	
	public void setRunTimeHour(String hour) {
		this.adapter.setRuntime(hour);
	}
	
	public String getRunTimeHour() {
		return this.adapter.getRuntime();
	}
	
	public void setWeeklyReport(boolean value) {
		this.adapter.setWeekly(value);
	}
	
	public boolean isWeeklyReport() {
		return this.adapter.isWeekly();
	}
	
	public void buildPanel(JFrame frame) {
		this.panel.build(frame, this);
		this.panel.setEnabled(false);
	}
	
	
	
	
}
