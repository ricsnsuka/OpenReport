package lib.structs;

import java.util.HashMap;

import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.adapters.applications.ApplicationsAdapter;
import lib.exceptions.OpenReportException;
import lib.gui.blocks.applications.ApplicationsPanel;

public class ReportConfig {
	private SeverityTypeAdapter severityTypes;
	private ScheduleAdapter schedule;
	private HashMap<String,ApplicationsAdapter> applications;
	
	public ReportConfig() {
		this(null, null);
	}
	
	public ReportConfig(SeverityTypeAdapter severityTypes, ScheduleAdapter schedule) {
		this.severityTypes = severityTypes;
		this.schedule = schedule;
		this.applications = new HashMap<>();
		init();
	}
	
	private void init() {
		this.applications.put(ApplicationsPanel.eBrokerAppliaction, null);
		this.applications.put(ApplicationsPanel.activeQuoteApplication, null);
		this.applications.put(ApplicationsPanel.openQuoteApplication, null);
		this.applications.put(ApplicationsPanel.openCostumerPortalApplication, null);
	}

	public SeverityTypeAdapter getSeverityTypes() {
		return severityTypes;
	}

	public ScheduleAdapter getSchedule() {
		return schedule;
	}

	public HashMap<String, ApplicationsAdapter> getApplications() {
		return applications;
	}

	public void setSeverityTypes(SeverityTypeAdapter severityTypes) {
		this.severityTypes = severityTypes;
	}

	public void setSchedule(ScheduleAdapter schedule) {
		this.schedule = schedule;
	}

	public void setApplications(String key, ApplicationsAdapter value) throws OpenReportException {
		if(validateApplicationKey(key)) {
			this.applications.put(key, value);
		} else {
			//LOG
			throw new OpenReportException("Application key is invalid");
		}
	}
	
	private boolean validateApplicationKey(String key) {
		return this.applications.containsKey(key);
	}
	
	
	
	
}
