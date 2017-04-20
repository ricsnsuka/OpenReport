package test;

import java.util.Map;

import lib.structs.LogData;
import lib.structs.LogEntryType;
import lib.structs.LogReport;
import lib.structs.ReportConfig;

public class FileParserTest {
	private static int occurrences = 0;
	public static void main(String[] args) {
//		run();

	}

	public static void run(ReportConfig config) {
		long start = System.currentTimeMillis();
		LogReport report = new LogReport();
		report.generateReport(config);

		System.out.println("--------------------------------------------------");

		LogEntryType [] types = LogEntryType.values();

		for(LogEntryType type : types) {
			occurrences = 0;
			report.getLogDataHits(config).entrySet().stream()
			.filter(map -> type.equals(map.getKey().getType())).forEach(map -> {
				occurrences += map.getValue();
			});
			System.out.println(type + ": " + occurrences + " occurrences");
		}

		System.out.println("--------------------------------------------------");

		for(Map.Entry<LogData, Integer> entry : report.getLogDataHits(config).entrySet()) {
			String sevInfo = entry.getKey().getSeverityInfo();
			System.out.println(sevInfo + ": " + report.countHits(sevInfo) + " times");
		}

		System.out.println("--------------------------------------------------");
		long finish = System.currentTimeMillis();
		System.out.println("Ran in " + (finish-start) + " ms");
	}

}
