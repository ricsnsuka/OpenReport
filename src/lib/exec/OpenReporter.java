/**
 * 
 */
package lib.exec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import lib.controller.EmailController;
import lib.json.ReportJSONFileWriter;
import lib.structs.LogData;
import lib.structs.LogEntryType;
import lib.structs.LogReport;
import lib.structs.ReportConfig;

/**
 * @author Ricardo Nsuka
 *
 */
public class OpenReporter implements Runnable {
	private Thread t;
	private String threadName;
	
	private ReportConfig config;
	private EmailController email;
	private int occurrences = 0;
	
	public OpenReporter(ReportConfig config, EmailController email, String name) {
		this.threadName = name;
		this.config = config;
		this.email = email;
	}


	@Override
	public void run() {
		System.out.println("Thread " + threadName + " started running");
		try {
	         for(int i = 5; i > 0; i--) {
	            System.out.println("Thread: " + threadName + ", " + i);
	            // Let the thread sleep for a while.
	            Thread.sleep(1000);
	         }
	      }catch (InterruptedException e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
		long start = System.currentTimeMillis();
		LogReport report = new LogReport();
		
		
		report.generateReport(config);
		
		Map<LogData, Integer> reportLogDataHits = report.getLogDataHits(config);
		
		System.out.println("--------------------------------------------------");
		System.out.println(threadName + "\n\n");
		
		LogEntryType [] types = LogEntryType.values();
		
		for(LogEntryType type : types) {
			occurrences = 0;
			reportLogDataHits.entrySet().stream()
				.filter(map -> type.equals(map.getKey().getType())).forEach(map -> {
					occurrences += map.getValue();
				});
			System.out.println(type + ": " + occurrences + " occurrences");
		}
		
		System.out.println("---------------------ORDERED---------------------");
		
		Map<LogData, Integer> result = new LinkedHashMap<>();
		
		reportLogDataHits.entrySet().stream().sorted(Map.Entry.<LogData, Integer>comparingByValue().reversed()).forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
		
		result.forEach((key, value) -> System.out.println("(" + key.getType() + (key.getType().name().length() >= 6 ?")\t":")\t\t") + key.getSeverityInfo() + ": " + value + " times."));

		
		System.out.println("--------------------------------------------------");
		
		
		long finish = System.currentTimeMillis();
		System.out.println("Ran in " + (finish-start) + " ms");
		
		
		System.out.println("Sending to: ");
		for(String developer : email.getEmailReceiversList()) {
			System.out.println(developer);
		}
		
		System.out.println("Creating JSON file");
		
		ReportJSONFileWriter json = new ReportJSONFileWriter();
		
		try {
			json.createFile(config, report.getAppLog());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
