package lib.json;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lib.structs.LogData;
import lib.structs.ReportConfig;

public class ReportJSONFileWriter {
	private JSONObject object;
	private JSONArray array;
	
	public ReportJSONFileWriter() {
		object = new JSONObject();
		array = new JSONArray();
	}
	
	public void createFile(ReportConfig config, Map<LogData, Integer> reportData) {
		
	}
	
}
