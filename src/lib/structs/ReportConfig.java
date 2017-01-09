package lib.structs;

import lib.controllers.ApplicationsController;
import lib.controllers.ScheduleController;
import lib.controllers.SeverityTypeController;

public class ReportConfig {
	private SeverityTypeController severityType;
	private ScheduleController schedule;
	private ApplicationsController applications;
	
	public ReportConfig(SeverityTypeController severityType, ScheduleController schedule, ApplicationsController applications) {
		this.severityType = severityType;
		this.schedule = schedule;
		this.applications = applications;
	}

	public SeverityTypeController getSeverityType() {
		return severityType;
	}

	public ScheduleController getSchedule() {
		return schedule;
	}

	public ApplicationsController getApplications() {
		return applications;
	}
	
	
	
}
