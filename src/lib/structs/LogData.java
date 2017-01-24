package lib.structs;

public class LogData {

	private String severityInfo;
	private LogEntryType type;
	
	public LogData(String severityInfo, LogEntryType type) {
		this.severityInfo = severityInfo;
		this.setType(type);
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

}
