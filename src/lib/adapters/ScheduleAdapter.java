package lib.adapters;

import java.util.ArrayList;

public class ScheduleAdapter {
	private boolean weekly;
	private ArrayList<String> weekdays;
	private String runtime;
	
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public ScheduleAdapter() {
	}
	
	public boolean isWeekly() {
		return weekly;
	}

	public void setWeekly(boolean weekly) {
		this.weekly = weekly;
	}

	public ArrayList<String> getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(ArrayList<String> weekdays) {
		this.weekdays = weekdays;
	}

}
