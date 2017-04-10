package lib.fileparser;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogReaderTest {
	LogReader logReader;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String filePathString = "C://Users//rnsuka//workspace//OpenReportsJAVA//src//resources//catalina.out";
		Path filePath = Paths.get(new URI(filePathString));
		logReader = new LogReader("Test", filePath);
	}
	
	@Test
	public void testGetReadingData() throws InterruptedException {
		logReader.start();
		this.wait();
		
		logReader.getReadingData();
		
		assert(true);
		
		
	}

}
