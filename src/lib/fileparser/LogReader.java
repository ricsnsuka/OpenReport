package lib.fileparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import lib.exceptions.OpenReportException;
import lib.structs.LogData;
import lib.structs.LogDataFactory;
import lib.structs.LogEntryType;

public class LogReader {

	private Map<LogData, Integer> logDataMap;
	private Path filePath;
	private String appRefName;

	public LogReader(String name, Path filePath) {
		this.logDataMap = new HashMap<>();
		this.filePath = filePath;
		this.appRefName = name;
	}

	public void read() {
		ReportXMLParser parser = new ReportXMLParser();
		try (Stream<String> fileLines = Files.lines(filePath)){
			fileLines.filter(line -> hasSeverity(line)).forEach(line -> {
				LogData logData = processLine(parser, line);
				if(logDataMap.containsKey(logData)) {
					logDataMap.put(logData, logDataMap.get(logData)+1);
				}
				else {
					logDataMap.put(logData, 1);
				}
			});
		} catch (IOException ex) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Map<LogData, Integer> getReadingData() {
		return logDataMap;
	}

	public String getAppRefName() {
		return appRefName;
	}

	private boolean hasSeverity(String line) {
		return line.startsWith("SEVERE") || line.startsWith("INFO") || line.startsWith("WARNING");
	}

	private LogData processLine(ReportXMLParser parser, String line) {
		LogData logData = null;
		try {
			logData = LogDataFactory.generateLogData(line);
			LogEntryType currentLogEntryType = logData.getType();
			String currentEntrySeverityInfo = logData.getSeverityInfo();
			String textContent;

			if((textContent = parser.exists(currentLogEntryType, currentEntrySeverityInfo)) == null) {
				if(!currentEntrySeverityInfo.contains("SessionReference")) {
					parser.addInfoToXML(currentLogEntryType.name(), currentEntrySeverityInfo);
				} 
			} else {
				logData.setSeverityInfo(textContent);
			}
		} catch(OpenReportException ex) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, null, ex);
		}
		return logData;
	}
}
