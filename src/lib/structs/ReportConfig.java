package lib.structs;

import java.util.HashMap;

import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.adapters.applications.ApplicationsAdapter;
import lib.exceptions.OpenReportException;
import lib.gui.blocks.applications.specific.ActiveQuoteApplicationPanel;
import lib.gui.blocks.applications.specific.eBrokerApplicationPanel;

public class ReportConfig {
	private SeverityTypeAdapter severityType;
	private ScheduleAdapter schedule;
	private HashMap<String,ApplicationsAdapter> applications;
	
	public ReportConfig() {
		this(null, null);
	}
	
	public ReportConfig(SeverityTypeAdapter severityType, ScheduleAdapter schedule) {
		this.severityType = severityType;
		this.schedule = schedule;
		this.applications = new HashMap<>();
		init();
	}
	
	private void init() {
		this.applications.put(eBrokerApplicationPanel.eBroker_ProductTitle, null);
		this.applications.put(ActiveQuoteApplicationPanel.AQ_ProductTitle, null);
	}

	public SeverityTypeAdapter getSeverityType() {
		return severityType;
	}

	public ScheduleAdapter getSchedule() {
		return schedule;
	}

	public HashMap<String, ApplicationsAdapter> getApplications() {
		return applications;
	}

	public void setSeverityType(SeverityTypeAdapter severityType) {
		this.severityType = severityType;
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
