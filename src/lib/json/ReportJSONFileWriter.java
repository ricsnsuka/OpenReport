package lib.json;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lib.structs.LogData;
import lib.structs.LogEntryType;
import lib.structs.ReportConfig;

public class ReportJSONFileWriter {
	private JsonObject object;
	private JsonArray details;
	private JsonArray severity;

	public ReportJSONFileWriter() {
		object = new JsonObject();
		severity = new JsonArray();
	}

	/**
	 * @param config
	 * @param reportData
	 * @throws IOException
	 */
	public void createFile(ReportConfig config, Map<String, Map<LogData, Integer>> reportData) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
		JsonParser jp = new JsonParser();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
//		JsonElement reportDate = jp.parse(dateFormat.format(date));
		
//		object.add("Report", reportDate);

		LogEntryType [] types = LogEntryType.values();
		for(LogEntryType type : types) {
			reportData.forEach((appName, logDataReport) -> {
				details = new JsonArray();
				logDataReport.entrySet().stream()
				.filter(map -> type.equals(map.getKey().getType()))
				.forEach(map -> {
					details.add(map.getKey().getSeverityInfo() + ": " + map.getValue());
				});
				severity.add(type.name() + ": " + details);
				object.add(appName, severity);
			});
			
		}

		try (FileWriter file = new FileWriter("/Users/rnsuka/Documents/file1.json")) {
			file.write(gson.toJson(object));
			System.out.println("Successfully Copied JSON Object to File...");
		} 
	}

}
