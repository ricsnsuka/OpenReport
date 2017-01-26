package lib.structs;

import java.util.HashMap;
import java.util.Set;

import lib.adapters.ApplicationAdapter;
import lib.adapters.ScheduleAdapter;
import lib.adapters.SeverityTypeAdapter;
import lib.exceptions.OpenReportException;
import lib.gui.blocks.applications.ApplicationsPanel;

public class ReportConfig implements Cloneable {
	private SeverityTypeAdapter severityTypes;
	private ScheduleAdapter schedule;
	private HashMap<String,ApplicationAdapter> applications;
	
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
		this.applications.put(ApplicationsPanel.activeQuote4PowerplaceApplication, null);
		this.applications.put(ApplicationsPanel.enrichmentHUBApplication, null);
		this.applications.put(ApplicationsPanel.hostedListServiceApplication, null);
		this.applications.put(ApplicationsPanel.openClientCheckApplication, null);
		this.applications.put(ApplicationsPanel.openDataWarehouseApplication, null);
		this.applications.put(ApplicationsPanel.openCostumerPortalApplication, null);
		this.applications.put(ApplicationsPanel.openQuoteApplication, null);
		this.applications.put(ApplicationsPanel.openUnitMeterApplication, null);
		this.applications.put(ApplicationsPanel.rteDeployerApplication, null);
		this.applications.put(ApplicationsPanel.quoteGenerationServiceApplication, null);
	}

	public SeverityTypeAdapter getSeverityTypes() {
		return severityTypes;
	}

	public ScheduleAdapter getSchedule() {
		return schedule;
	}

	public HashMap<String, ApplicationAdapter> getApplications() {
		return applications;
	}

	public void setSeverityTypes(SeverityTypeAdapter severityTypes) {
		this.severityTypes = severityTypes;
	}

	public void setSchedule(ScheduleAdapter schedule) {
		this.schedule = schedule;
	}

	public void setApplications(String key, ApplicationAdapter value) throws OpenReportException {
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
	
	@Override
	public ReportConfig clone() {
		ReportConfig clone = new ReportConfig();
		
		clone.setSeverityTypes(getSeverityTypes().clone());
		clone.setSchedule(getSchedule().clone());
		
		Set<String> keySet = getApplications().keySet();
		
		for(String key : keySet) {
			try {
				clone.setApplications(key, getApplications().get(key).clone());
			} catch (OpenReportException e) {
				e.printStackTrace();
			}
		}
		
		return clone;
	}
	
	public int countApplications() {
		int ret = 0;
		for(String key : applications.keySet()) {
			ret += applications.get(key).getSelectedValues().size();
		}
		return ret;
	}
	
}
