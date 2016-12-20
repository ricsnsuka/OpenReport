package lib.structs;

import java.util.ArrayList;
import java.util.HashMap;

import lib.fileparser.FileParser;

public class LogReport {
	private HashMap<String, Integer> hits;
	private ArrayList<LogData> logData;
	private HashMap<String, Integer> logDataHits;
	
	public LogReport() {
		hits = new HashMap<>();
		logDataHits = new HashMap<>();
		init();
	}
	
	public LogReport(ArrayList<LogData> logData) {
		this();
		this.logData = logData;
		
	}
	
	private void init() {
		hits.put("SEVERE", 0);
		hits.put("INFO", 0);
		hits.put("WARNING", 0);
	}
	
	public void generateReport(String filename) {
		FileParser parser = new FileParser();
		logData = parser.parse(filename);
	}
	
	public void countHits() {
		for(LogData data : logData) {
			if(data.getType() == LogType.SEVERE)
				hits.replace("SEVERE", hits.get("SEVERE")+1);
			if(data.getType() == LogType.INFO)
				hits.replace("INFO", hits.get("INFO")+1);
			if(data.getType() == LogType.WARNING)
				hits.replace("WARNING", hits.get("WARNING")+1);
		}
	}
	
	public HashMap<String, Integer> getHits() {
		return this.hits;
	}
	
	public void beautify() {
		for(LogData data: logData) {
			if(!logDataHits.containsKey(data)) {
				logDataHits.put(data.getSeverityInfo(), 1);
			}
			else {
				logDataHits.replace(data.getSeverityInfo(), logDataHits.get(data)+1);
			}
		}
	}
	
	public HashMap<String, Integer> getLogDataHits() {
		return this.logDataHits;
	}

}
