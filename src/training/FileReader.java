package training;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

	public FileReader() {
	}
	
	public static void main(String[] args) {
		fileReading("test.txt");
	}


	public static void fileReading(String filename) {
		try {
			Charset charset = Charset.forName("US-ASCII");
			Path path = FileSystems.getDefault().getPath("src\\resources", filename);
			BufferedReader br = Files.newBufferedReader(path, charset);
			
			String line = null;
			System.out.println("----START WHILE----");
			while((line = br.readLine()) != null) {
				doSomethingWithLine(line);
				
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
	
	public static void doSomethingWithLine(String line) {
		String[] splitted = line.split(" ");
		if(startsWithMonth(line)) {
			int i = 0;
			String[] passer = new String[5];
			while(i < passer.length) {
				passer[i] = splitted[i];
				i++;
			}
			System.out.println(arrayToString(passer));
		}
		if(hasSeverity(line)) {
			System.out.println(splitted[0]);
			System.out.println(line.substring(splitted[0].length() + 1));
		}
	}
	
	private static String arrayToString(String[] array) {
		String ret = "";
		for(String pos: array) {
			ret = ret + pos + " ";
		}
		return ret;
	}
	
	public static boolean startsWithMonth(String line) {
		return  line.startsWith("Jan") || line.startsWith("Feb") || line.startsWith("Mar") || line.startsWith("Apr")
				|| line.startsWith("May") || line.startsWith("Jun") || line.startsWith("Jul") || line.startsWith("Aug")
				|| line.startsWith("Sep") || line.startsWith("Oct") || line.startsWith("Nov") || line.startsWith("Dec");
	}
	
	public static boolean hasSeverity(String line) {
		return line.startsWith("SEVERE") || line.startsWith("INFO") || line.startsWith("WARNING");
	}

}
