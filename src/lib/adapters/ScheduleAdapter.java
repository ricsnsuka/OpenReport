package lib.adapters;

import java.util.ArrayList;

public class ScheduleAdapter implements Cloneable {
	private boolean weekly;
	private ArrayList<String> weekdays;
	private String runtime;
	
	public ScheduleAdapter() {
		weekdays = new ArrayList<>();
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
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

	public ScheduleAdapter clone() {
		ScheduleAdapter clone = new ScheduleAdapter();
		clone.setRuntime(getRuntime());
		clone.setWeekly(isWeekly());

		ArrayList<String> cloneWeekdays = new ArrayList<>();
		cloneWeekdays.addAll(getWeekdays());
		
		clone.setWeekdays(cloneWeekdays);
		
		return clone;
		
	}

}
