package lib.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lib.exceptions.OpenReportException;
import lib.others.MutableString;
import lib.structs.LogData;
import lib.structs.LogDataFactory;
import lib.structs.LogEntryType;

public class FileParser {
	public FileParser() {
	}

	public ArrayList<LogData> parse(String filename) {
		ArrayList<LogData> logDataCollection = new ArrayList<>();
		XMLParser parser = new XMLParser();

		boolean ready = false;
		String timestamp = null;
		String exception = "";

//		Charset charset = Charset.forName("windows-1250");
//		Path path = FileSystems.getDefault().getPath("src\\resources", filename);
		//			BufferedReader br = Files.newBufferedReader(path, charset);
		int i = 0;
		try (Scanner scan = new Scanner(new File("src\\resources\\"+filename))) {
			String line = null;
			System.out.println(" - SCANNING");
			while(scan.hasNextLine()) {
				exception = (line = scan.nextLine());
				if(startsWithMonth(line)) {
					ready = true;
					timestamp = extractTimestamp(line);
				}
				else {
					if (ready) {
						if(hasSeverity(line)) {
							String[] severity = extractSeverityInfo(line);
							LogData logData = LogDataFactory.generateLogData(severity[0], timestamp, severity[1]);
							LogEntryType currentLogEntryType = logData.getType();
							String currentEntrySeverityInfo = logData.getSeverityInfo();
							MutableString mutable = new MutableString();
							logDataCollection.add(logData);
							if(!parser.exists(currentLogEntryType, currentEntrySeverityInfo, mutable)) {
								parser.addInfoToXML(currentLogEntryType, currentEntrySeverityInfo);
							} else {
								logData.setSeverityInfo(mutable.getString());
							}
							ready = false;
						}
					}
				}
				if((i % 359500) == 0) {
					System.out.print(".");
				}
				i++;
				
			}
			System.out.println(" - FINISHED");
			scan.close();
		}catch(OpenReportException ex) {
			//LOG?
			ex.printStackTrace();

		}catch(IOException e ) {
			System.out.println(exception);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return logDataCollection;
	}



	private boolean startsWithMonth(String line) {
		return  line.startsWith("Jan") || line.startsWith("Feb") || line.startsWith("Mar") || line.startsWith("Apr")
				|| line.startsWith("May") || line.startsWith("Jun") || line.startsWith("Jul") || line.startsWith("Aug")
				|| line.startsWith("Sep") || line.startsWith("Oct") || line.startsWith("Nov") || line.startsWith("Dec");
	}

	private String extractTimestamp(String line) {
		String[] splittedLine = line.split(" ");
		String ret = "";
		for(int i = 0; i < 5; i++) {
			ret = ret + splittedLine[i] + " ";
		}
		return ret;
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
