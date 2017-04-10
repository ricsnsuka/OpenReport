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

public class LogReader implements Runnable {

	private Thread thread;
	private String threadName;

	private Map<LogData, Integer> logDataMap;
	private Path filePath;

	public LogReader(String name, Path filePath) {
		this.logDataMap = new HashMap<>();
		this.filePath = filePath;
		this.threadName = name;
	}

	@Override
	public void run() {
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
			logDataMap.forEach((key, value) -> System.out.printf("%s = %s\n", key.getSeverityInfo(), value));
		} catch (IOException ex) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void start() {
		if(thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	public Map<LogData, Integer> getReadingData() {
		return logDataMap;
	}

	public String getThreadName() {
		return threadName;
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
				parser.addInfoToXML(currentLogEntryType.name(), currentEntrySeverityInfo);
			} else {
				logData.setSeverityInfo(textContent);
			}
		} catch(OpenReportException ex) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, null, ex);
		}
		return logData;
	}
}
