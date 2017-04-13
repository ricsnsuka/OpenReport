package lib.fileparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import lib.structs.Application;
import lib.structs.LogData;

public class CatalinaLogParser {
	private Map<LogData, Integer> logDataMap = new HashMap<>();
	
	public void parse(Map<String, Map<LogData, Integer>> appLogData, Application application) {

		String appPath = "src/resources/applications/"+application.getProduct()+"/"+application.getName();
		String appName = application.getProduct() + " - " + application.getName();
		
		
		try(Stream<Path> paths = Files.walk(Paths.get(appPath))) {
			paths.filter(filePath -> Files.isRegularFile(filePath)).forEach(filePath -> {
				LogReader logReader = new LogReader(application.getProduct(), filePath);
				logReader.read();
				logReader.getReadingData().forEach((key, value) -> {
					if(logDataMap.containsKey(key)) {
						logDataMap.replace(key, logDataMap.get(key)+value);
					}
					else {
						logDataMap.put(key, value);
					}
				});
			});
			appLogData.put(appName, logDataMap);
		} catch (IOException e) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, "Resource not available:: ", application.getName());
		}	
	}

}
