package lib.structs;

public class InfoLogData extends LogData {
	public final static LogEntryType type = LogEntryType.INFO;
	public InfoLogData(String severityInfo) {
		super(severityInfo, type);
	}
	
}
