package lib.structs;

public class InfoLogData extends LogData {
	public final static LogType type = LogType.INFO;
	public InfoLogData(String logDate, String severityInfo) {
		super(logDate, severityInfo, type);
	}
	
	@Override
	public LogReport generateReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
