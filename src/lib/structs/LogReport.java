package lib.structs;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lib.fileparser.CatalinaLogParser;

public class LogReport {
	private Map<LogData, Integer> logDataHits;

	public LogReport() {
		logDataHits = new HashMap<>();
	}

	public void generateReport(ReportConfig config) {
		CatalinaLogParser parser = new CatalinaLogParser();
		for(String key : config.getApplications().keySet()) {
			for(Application application : config.getApplications().get(key).getSelectedValues()) {
				logDataHits = parser.parse(application);
			}
		}

	}

	public int countHits(String sevInfo) {
		return logDataHits.get(sevInfo);
	}
	
	public Map<LogData, Integer> getLogDataHits() {
		return this.logDataHits;
	}

	public Map<LogData, Integer> getLogDataHits(ReportConfig config) {
		return this.logDataHits.entrySet().stream()
				.filter(map -> filter(config, map.getKey()))
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
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
