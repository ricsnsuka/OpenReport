package lib.structs;

import lib.adapters.ApplicationsAdapter;
import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;

public class ReportConfig {
	private SeverityTypeAdapter severityType;
	private ScheduleAdapter schedule;
	private ApplicationsAdapter applications;
	
	public ReportConfig(SeverityTypeAdapter severityType, ScheduleAdapter schedule, ApplicationsAdapter applications) {
		this.severityType = severityType;
		this.schedule = schedule;
		this.applications = applications;
	}

	public SeverityTypeAdapter getSeverityType() {
		return severityType;
	}

	public ScheduleAdapter getSchedule() {
		return schedule;
	}

	public ApplicationsAdapter getApplications() {
		return applications;
	}
	
	
	
}
