package lib.fileparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import lib.structs.Application;
import lib.structs.LogData;

public class CatalinaLogParser {

	public ArrayList<LogData> parse( Application application) {
		ArrayList<LogData> logDataCollection = new ArrayList<>();

		String appPath = "src/resources/applications/"+application.getProduct()+"/"+application.getName();

		try(Stream<Path> paths = Files.walk(Paths.get(appPath))) {
			paths.filter(filePath -> Files.isRegularFile(filePath)).forEach(filePath -> {
//				Map<LogData, Integer> logDataMap;
				LogReader logReader = new LogReader(application.getProduct(), filePath);
				logReader.start();
				
			});
		} catch (IOException e) {
			Logger.getLogger(CatalinaLogParser.class.getName()).log(Level.SEVERE, "Resource not available:: ", e);
		}	
		return logDataCollection;
	}

}
