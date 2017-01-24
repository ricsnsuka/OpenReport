package lib.structs;

import lib.exceptions.OpenReportException;

public class LogDataFactory {

	public LogDataFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static LogData generateLogData(String type,  String severityInfo) throws OpenReportException {
		LogData log = null;
		switch(type) {
		case "SEVERE":
			log = new SevereLogData(severityInfo);
			break;
		case "INFO":
			log = new InfoLogData(severityInfo);
			break;
		case "WARNING":
			log = new WarningLogData(severityInfo);
			break;
		default:
			throw new OpenReportException("The provided type is invalid.");
		}
		return log;
	}

}
