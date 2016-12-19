package lib.structs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class LogData {

	private Date logTime;
	private String fault;
	private LogType logType;
	
	public LogData(String logTime, String fault, LogType logType) {
		this.logTime = null;
		setLogTime(logTime);
		this.fault = fault;
		this.logType = logType;
	}

	private boolean validLogTime(String logTime) {
		return logTime.length() == 23 || logTime.length() == 24;
	}

	public Date getLogTime() {
		return logTime;
	}

	private void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public void setLogTime(String logTime) {
		if (validLogTime(logTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.US);
			try {
				setLogTime(sdf.parse(logTime));
			} catch (ParseException e) {
				// LOG
			} 
		}
	}

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public abstract LogReport generateReport();

}
