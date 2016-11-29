package lib.structs;

import lib.exceptions.OpenReportException;

public class LogDataFactory {

	public LogDataFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static LogData generateLogData(LogType type) throws OpenReportException {
		switch(type) {
		case SEVERE:
			
			break;
		case INFO:
			
			break;
		default:
			throw new OpenReportException("The provided type is invalid.");
		}
	}

}
