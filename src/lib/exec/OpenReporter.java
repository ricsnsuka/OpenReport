/**
 * 
 */
package lib.exec;

import lib.controller.EmailController;
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
		
		System.out.println("--------------------------------------------------");
		System.out.println(threadName + "\n\n");
		
		LogEntryType [] types = LogEntryType.values();
		
		for(LogEntryType type : types) {
			occurrences = 0;
			report.getLogDataHits().entrySet().stream()
				.filter(map -> type.equals(map.getKey().getType())).forEach(map -> {
					occurrences += map.getValue();
				});
			System.out.println(type + ": " + occurrences + " occurrences");
		}
		
		System.out.println("--------------------------------------------------");
		
		report.getLogDataHits(config).forEach((key, value) -> System.out.println("(" + key.getType() + ")\t" + key.getSeverityInfo() + ": " + value + " times."));
		
		System.out.println("--------------------------------------------------");
		long finish = System.currentTimeMillis();
		System.out.println("Ran in " + (finish-start) + " ms");
		
		System.out.println("Sending to: ");
		for(String developer : email.getEmailReceiversList()) {
			System.out.println(developer);
		}
		
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
