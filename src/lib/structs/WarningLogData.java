package lib.structs;

public class WarningLogData extends LogData {
	public final static LogEntryType type = LogEntryType.WARNING;
	public WarningLogData(String logDate, String severityInfo) {
		super(logDate, severityInfo, type);
	}
}
