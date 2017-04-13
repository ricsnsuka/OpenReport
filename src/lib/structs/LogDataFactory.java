package lib.structs;

import lib.exceptions.OpenReportException;

public class LogDataFactory {

	public static LogData generateLogData(String line) throws OpenReportException {
		LogData log = null;
		int split = line.indexOf(':');
		switch(line.substring(0, split)) {
		case "SEVERE":
			log = new SevereLogData(line.substring(split+1).trim());
			break;
		case "INFO":
			log = new InfoLogData(line.substring(split+1).trim());
			break;
		case "WARNING":
			log = new WarningLogData(line.substring(split+1).trim());
			break;
		default:
			throw new OpenReportException("The provided type is invalid.");
		}
		return log;
	}

}
