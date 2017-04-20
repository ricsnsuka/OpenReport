package lib.structs;

import java.util.HashMap;
import java.util.Map;

import lib.fileparser.CatalinaLogParser;

public class LogReport {
	private Map<LogData, Integer> logDataHits;
	private Map<String, Map<LogData, Integer>> appLogDataHits;
	private int hitOccurences = 0;

	public LogReport() {
		appLogDataHits = new HashMap<>();
	}

	public void generateReport(ReportConfig config) {
		CatalinaLogParser parser = new CatalinaLogParser();
		for(String key : config.getApplications().keySet()) {
			for(Application application : config.getApplications().get(key).getSelectedValues()) {
				parser.parse(appLogDataHits, application);
			}
		}
	}

	public int countHits(String sevInfo) {
		hitOccurences = 0;
		appLogDataHits.forEach((key, value) -> value.entrySet().stream()
				.filter(map -> sevInfo.equals(map.getKey().getType().name())));
		return logDataHits.get(sevInfo);
	}
	
//	public Map<LogData, Integer> getLogDataHits() {
//		return this.logDataHits;
//	}
//
//	public Map<LogData, Integer> getLogDataHits(ReportConfig config) {
//		return this.logDataHits.entrySet().stream()
//				.filter(map -> filter(config, map.getKey()))
//				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
//	}
	
	public Map<LogData, Integer> getLogDataHits(ReportConfig config) {
		logDataHits = new HashMap<>();
		appLogDataHits.forEach((key, value) -> value.entrySet().stream()
				.filter(map -> filter(config, map.getKey()))
				.forEach(logData -> logDataHits.put(logData.getKey(), logData.getValue())));
		return logDataHits;
	}
	
	public Map<String, Map<LogData, Integer>> getAppLog() {
		return appLogDataHits;
	}
	
	private boolean filter(ReportConfig config, LogData logData) {
		if(config.getSeverityTypes().isAllTypes()) {
			return true;
		}
		switch(logData.getType()) {
		case INFO:
			return config.getSeverityTypes().isInfo();
		case SEVERE:
			return config.getSeverityTypes().isSevere();
		case WARNING:
			return config.getSeverityTypes().isWarning();
		default:
			break;
		}
		return false;
	}
}
