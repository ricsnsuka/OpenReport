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
	
	@Override
	public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass()) {
			return false;
		}
		return ((LogData)other).getType().name().equals(this.getType().name()) && ((LogData)other).getSeverityInfo().equals(this.getSeverityInfo());
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.severityInfo != null ? this.severityInfo.hashCode() : 0);
		hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
		return hash;
	}

}
