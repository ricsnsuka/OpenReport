package lib.fileparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lib.adapters.SeverityTypeAdapter;
import lib.exceptions.OpenReportException;
import lib.others.MutableString;
import lib.structs.Application;
import lib.structs.LogData;
import lib.structs.LogDataFactory;
import lib.structs.LogEntryType;
import lib.structs.ReportConfig;

public class CatalinaLogParser {
	public CatalinaLogParser() {
	}

	public ArrayList<LogData> parse(ReportConfig config, Application application) {
		ArrayList<LogData> logDataCollection = new ArrayList<>();
		ReportXMLParser parser = new ReportXMLParser();

		boolean ready = false;
		String filepath = "src\\resources\\applications\\"+application.getProduct()+"\\"+application.getName()+"\\catalina.log";
		try (Scanner scan = new Scanner(new File(filepath))) {
			String line = null;
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				if(startsWithMonth(line)) {
					ready = true;
				}
				else {
					if (ready) {
						if(hasSeverity(line)) {
							String[] severity = extractSeverityInfo(line);
							if(filter(config.getSeverityTypes(), severity[0])) {
								processLine(parser, severity[0], severity[1], logDataCollection);
							}
							ready = false;
						}
					}
				}
			}
			scan.close();
		}catch(FileNotFoundException ex ) {
			//LOG
			System.err.println("Resource not available:: " + filepath);
		}
		return logDataCollection;
	}
	
	private boolean filter(SeverityTypeAdapter severity, String type) {
		if(severity.isAllTypes()) {
			return true;
		}
		switch(type) {
		case "INFO":
			return severity.isInfo();
		case "SEVERE":
			return severity.isSevere();
		case "WARNING":
			return severity.isWarning();
		default:
			break;
		}
		return false;
	}

	private void processLine(ReportXMLParser parser, String type, String desc, ArrayList<LogData> logDataCollection) {
		try {
			LogData logData = LogDataFactory.generateLogData(type, desc);
			LogEntryType currentLogEntryType = logData.getType();
			String currentEntrySeverityInfo = logData.getSeverityInfo();
			MutableString mutable = new MutableString();

			if(!parser.exists(currentLogEntryType, currentEntrySeverityInfo, mutable)) {
				parser.addInfoToXML(currentLogEntryType.name(), currentEntrySeverityInfo);
				System.out.println(currentEntrySeverityInfo);
			} else {
				logData.setSeverityInfo(mutable.getString());
			}
			logDataCollection.add(logData);
		} catch(OpenReportException ex) {
			//LOG
		}
	}

	private boolean startsWithMonth(String line) {
		return  line.startsWith("Jan") || line.startsWith("Feb") || line.startsWith("Mar") || line.startsWith("Apr")
				|| line.startsWith("May") || line.startsWith("Jun") || line.startsWith("Jul") || line.startsWith("Aug")
				|| line.startsWith("Sep") || line.startsWith("Oct") || line.startsWith("Nov") || line.startsWith("Dec");
	}

	private boolean hasSeverity(String line) {
		return line.startsWith("SEVERE") || line.startsWith("INFO") || line.startsWith("WARNING");
	}

	private String[] extractSeverityInfo(String line) {
		String[] splittedLine = line.split(" ");
		String[] ret = {determineLogType(splittedLine[0]), line.substring(splittedLine[0].length() + 1)};
		return ret;
	}

	private String determineLogType(String logType) {
		if(logType.equalsIgnoreCase("severe:"))
			return "SEVERE";
		else if(logType.equalsIgnoreCase("info:"))
			return "INFO";
		else if(logType.equalsIgnoreCase("warning:"))
			return "WARNING";
		else
			return null;
	}

}
