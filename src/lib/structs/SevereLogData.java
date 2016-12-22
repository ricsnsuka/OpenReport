package lib.structs;

public class SevereLogData extends LogData {
	public final static LogEntryType type = LogEntryType.SEVERE;
	public SevereLogData(String logDate, String severityInfo) {
		super(logDate, severityInfo, type);
	}


	@Override
	public LogReport generateReport() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean equals(Object object) {
		if(object instanceof SevereLogData) {
			if(((SevereLogData) object).getLogTime().equals(this.getLogTime()) &&
					((SevereLogData) object).getSeverityInfo().equals(this.getSeverityInfo())) {
				return true;
			}
		}
		return false;
	}

}
