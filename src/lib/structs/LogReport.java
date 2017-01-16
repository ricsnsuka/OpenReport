package lib.structs;

import java.util.ArrayList;
import java.util.HashMap;

import lib.fileparser.CatalinaLogParser;

public class LogReport {
	private HashMap<String, Integer> hits;
	private ArrayList<LogData> logData;
	private HashMap<String, Integer> logDataHits;
	
	public LogReport() {
		hits = new HashMap<>();
		logDataHits = new HashMap<>();
		init();
	}
	
	private void init() {
		hits.put("SEVERE", 0);
		hits.put("INFO", 0);
		hits.put("WARNING", 0);
	}
	
	private void generateReport() {
		String sevInfo = "";
		for(LogData data : logData) {
			sevInfo = data.getSeverityInfo();
			if(logDataHits.containsKey(sevInfo)) {
				logDataHits.replace(sevInfo, logDataHits.get(sevInfo)+1);
			} else {
				logDataHits.put(sevInfo, 1);
			}
		}
	}
	
	public void generateReport(ReportConfig config, String filename) {
		CatalinaLogParser parser = new CatalinaLogParser();
		logData = parser.parse(config, filename);
		generateReport();
	}
	
	public void countHits() {
		for(LogData data : logData) {
			if(data.getType() == LogEntryType.SEVERE)
				hits.replace("SEVERE", hits.get("SEVERE")+1);
			if(data.getType() == LogEntryType.INFO)
				hits.replace("INFO", hits.get("INFO")+1);
			if(data.getType() == LogEntryType.WARNING)
				hits.replace("WARNING", hits.get("WARNING")+1);
		}
	}
	
	public int countHits(String sevInfo) {
		return logDataHits.get(sevInfo);
	}
	
	public HashMap<String, Integer> getHits() {
		return this.hits;
	}
	
	public HashMap<String, Integer> getLogDataHits() {
		return this.logDataHits;
	}
	
	public int getSevereHits() {
		return hits.get(LogEntryType.SEVERE);
	}
	
	public int getInfoHits() {
		return hits.get(LogEntryType.INFO);
	}
	
	public int getWarningHits() {
		return hits.get(LogEntryType.WARNING);
	}

}
