package lib.structs;

import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.adapters.applications.ApplicationsAdapter;

public class ReportConfig {
	private SeverityTypeAdapter severityType;
	private ScheduleAdapter schedule;
	private ApplicationsAdapter applications;
	
	public ReportConfig() {
		this.severityType = null;
		this.schedule = null;
		this.applications = null;
	}
	
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

	public void setSeverityType(SeverityTypeAdapter severityType) {
		this.severityType = severityType;
	}

	public void setSchedule(ScheduleAdapter schedule) {
		this.schedule = schedule;
	}

	public void setApplications(ApplicationsAdapter applications) {
		this.applications = applications;
	}
	
	
	
	
}
