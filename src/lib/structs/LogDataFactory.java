package lib.structs;

import lib.exceptions.OpenReportException;

public class LogDataFactory {

	public LogDataFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static LogData generateLogData(LogType type) throws OpenReportException {
		LogData log = null;
		switch(type) {
		case SEVERE:
			log = new SevereLogData(null, null, null);
			break;
		case INFO:
			log = new InfoLogData(null, null, null);
			break;
		default:
			throw new OpenReportException("The provided type is invalid.");
		}
		return log;
	}

}
