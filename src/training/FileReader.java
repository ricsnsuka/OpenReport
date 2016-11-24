package training;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {

	public FileReader() {
	}
	
	public static void main(String[] args) {
		fileReading("test.txt");
		System.out.println(patternMatches());
	}


	public static void fileReading(String filename) {
		try {
			Charset charset = Charset.forName("US-ASCII");
			Path path = FileSystems.getDefault().getPath("src\\resources", filename);
			BufferedReader br = Files.newBufferedReader(path, charset);
			
			String line = null;
			System.out.println("----START WHILE----");
			while((line = br.readLine()) != null) {
				System.out.println(hasDate(line));
				
				
				
			}
			br.close();
			System.out.println("----END WHILE----");


		}catch(IOException e) {
			System.err.println(e.getMessage()+ "\n");
			e.printStackTrace();
		}finally {
			System.out.println("FINALLY");
		}
	}
	
	public static boolean hasDate(String line) {
		String filtered = null;
		if(line.length()>=24) {
			filtered = line.substring(0, 24);
		}
		else {
			filtered = line;
		}
		System.out.println(filtered);
		
		
		Pattern month = Pattern.compile("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)");
		Matcher matcher = month.matcher(filtered);
				
		return matcher.matches();
	}
	
	
	public static void extractLogData() {
		
	}
	
	
	public static boolean patternMatches() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.US);
		String test = "Oct 21, 2016 11:59:46 PM";
		System.out.println(test.length());
		char[] date_hour = new char[24];
		
		date_hour = test.toCharArray();
		
		System.out.println(date_hour);
		
		boolean ret = Pattern.matches(sdf.toPattern(), test);
		System.out.println(sdf.toPattern());
		System.out.println(sdf.toLocalizedPattern());
		return ret;
	}




}
