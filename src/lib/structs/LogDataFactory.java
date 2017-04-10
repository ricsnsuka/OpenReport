package lib.structs;

import lib.exceptions.OpenReportException;

public class LogDataFactory {

	public static LogData generateLogData(String line) throws OpenReportException {
		LogData log = null;
		
		switch(line.split(": ")[0]) {
		case "SEVERE":
			log = new SevereLogData(line.split(": ")[1]);
			break;
		case "INFO":
			log = new InfoLogData(line.split(": ")[1]);
			break;
		case "WARNING":
			log = new WarningLogData(line.split(": ")[1]);
			break;
		default:
			throw new OpenReportException("The provided type is invalid.");
		}
		return log;
	}

}
