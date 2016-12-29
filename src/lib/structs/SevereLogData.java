package lib.structs;

public class SevereLogData extends LogData {
	public final static LogEntryType type = LogEntryType.SEVERE;
	public SevereLogData(String logDate, String severityInfo) {
		super(logDate, severityInfo, type);
	}

}
