package lib.fileparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import lib.exceptions.OpenReportException;
import lib.structs.LogData;
import lib.structs.LogDataFactory;

public class FileParser {
	public FileParser() {
	}

	public ArrayList<LogData> parse(String filename) {
		ArrayList<LogData> logDataCollection = new ArrayList<>();
		try {
			boolean ready = false;
			String timestamp = null;
			
			Charset charset = Charset.forName("US-ASCII");
			Path path = FileSystems.getDefault().getPath("src\\resources", filename);
			BufferedReader br = Files.newBufferedReader(path, charset);
			
			
			String line = null;
			while((line = br.readLine()) != null) {
				if(startsWithMonth(line)) {
					ready = true;
					timestamp = extractTimestamp(line);
				}
				else {
					if (ready) {
						  if(hasSeverity(line)) {
							  String[] severity = extractSeverityInfo(line);
							  LogData logData = LogDataFactory.generateLogData(severity[0], timestamp, severity[1]);
							  logDataCollection.add(logData);
							  ready = false;
						  }
					}
				}
			}
			br.close();
		}catch(OpenReportException ex) {
			//LOG?
			ex.printStackTrace();

		}catch(IOException e) {
			//LOG?
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
