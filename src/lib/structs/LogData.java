package lib.structs;

public abstract class LogData {

	private String logTime;
	private String severityInfo;
	private LogEntryType type;
	
	public LogData(String logTime, String severityInfo, LogEntryType type) {
		this.logTime = logTime;
		this.severityInfo = severityInfo;
		this.setType(type);
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getSeverityInfo() {
		return severityInfo;
	}

	public void setSeverityInfo(String severityInfo) {
		this.severityInfo = severityInfo;
	}
	

	/**
	 * @return the type
	 */
	public LogEntryType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(LogEntryType type) {
		this.type = type;
	}

	public abstract LogReport generateReport();

}
