package lib.controller;

import java.util.ArrayList;

import lib.adapters.ScheduleAdapter;
import lib.structs.ReportConfig;

public class ScheduleController {
	private ScheduleAdapter scheduleAdapter;

	public ScheduleController(ReportConfig config, ScheduleAdapter scheduleAdapter) {
		this.scheduleAdapter = scheduleAdapter;
		config.setSchedule(scheduleAdapter);
	}
	
	public void addSelectedWeekday(String weekday) {
		this.scheduleAdapter.getWeekdays().add(weekday);
	}
	
	public void removeSelectedWeekday(String weekday) {
		this.scheduleAdapter.getWeekdays().remove(weekday);
	}
	
	public ArrayList<String> getSelectedWeekdays() {
		return this.scheduleAdapter.getWeekdays();
	}
	
	public void setRunTimeHour(String hour) {
		this.scheduleAdapter.setRuntime(hour);
	}
	
	public String getRunTimeHour() {
		return this.scheduleAdapter.getRuntime();
	}
	
	public void setWeeklyReport(boolean value) {
		this.scheduleAdapter.setWeekly(value);
	}
	
	public boolean isWeeklyReport() {
		return this.scheduleAdapter.isWeekly();
	}
	
	
	
	
}
