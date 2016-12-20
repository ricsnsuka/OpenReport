package lib.structs;

public abstract class LogData {

	private String logTime;
	private String severityInfo;
	private LogType type;
	
	public LogData(String logTime, String severityInfo, LogType type) {
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
	public LogType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(LogType type) {
		this.type = type;
	}

	public abstract LogReport generateReport();

}
