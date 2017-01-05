package test;

import java.util.HashMap;
import java.util.Map;

import lib.structs.LogReport;

public class FileParserTest {

	public static void main(String[] args) {
		run();
		
	}
	
	public static void run() {
		long start = System.currentTimeMillis();
		LogReport report = new LogReport();
		report.generateReport("catalina.out");
		report.countHits();
		HashMap<String, Integer> hits = report.getHits();
		
		System.out.println("--------------------------------------------------");
		
		for(Map.Entry<String, Integer> entry : hits.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " occurences");
		}
		
		System.out.println("--------------------------------------------------");
		
		for(Map.Entry<String, Integer> entry : report.getLogDataHits().entrySet()) {
			String sevInfo = entry.getKey();
			System.out.println(sevInfo + ": " + report.countHits(sevInfo) + " times");
		}
		
		System.out.println("--------------------------------------------------");
		long finish = System.currentTimeMillis();
		System.out.println("Ran in " + (finish-start) + " ms");
	}

}
