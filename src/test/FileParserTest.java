package test;

import java.util.HashMap;
import java.util.Map;

import lib.fileparser.FileParser;
import lib.structs.LogReport;

public class FileParserTest {

	public static void main(String[] args) {
		FileParser parser = new FileParser();
		
		long start = System.currentTimeMillis();
		LogReport report = new LogReport(parser.parse("catalina.out"));
		report.countHits();
		report.beautify();
		HashMap<String, Integer> hits = report.getHits();
		HashMap<String, Integer> logDataHits = report.getLogDataHits();
		
		for(Map.Entry<String, Integer> entry : hits.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " occurences");
		}
		
		for(Map.Entry<String, Integer> entry : logDataHits.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " occurences");
		}
		
		long finish = System.currentTimeMillis();
		
		System.out.println("Ran in " + (finish-start) + " ms");
	}

}
