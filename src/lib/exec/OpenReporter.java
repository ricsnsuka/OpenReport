/**
 * 
 */
package lib.exec;

import java.util.HashMap;
import java.util.Map;

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
	
	
	public OpenReporter(ReportConfig config, String name) {
		this.threadName = name;
		this.config = config;
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
		HashMap<String, Integer> hits = report.getHits();
		
		System.out.println("--------------------------------------------------");
		System.out.println(threadName + "\n\n");
		
		for(Map.Entry<String, Integer> entry : hits.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + " occurences");
		}
		
		System.out.println("--------------------------------------------------");
		
//		for(Map.Entry<String, Integer> entry : report.getLogDataHits().entrySet()) {
//			String sevInfo = entry.getKey();
//			System.out.println(sevInfo + ": " + report.countHits(sevInfo) + " times");
//		}
		
//		System.out.println("--------------------------------------------------");
		long finish = System.currentTimeMillis();
		System.out.println("Ran in " + (finish-start) + " ms");
		
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	
	
	
}
